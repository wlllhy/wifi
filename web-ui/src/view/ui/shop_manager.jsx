import React, { Component, PropTypes } from 'react'; // 引入了React和PropTypes
import { connect } from 'react-redux';
import { is, fromJS } from 'immutable';
import { RenderData } from '../../component/mixin';
import { Table, Input, Popconfirm, Form, message, Button, Row } from 'antd';
import EditableCell from '../../component/mixin/editableCell';
import Config from '../../component/mixin/Config';
import {Bcrumb} from "../../component/bcrumb/bcrumb";
import CollectionCreateForm from "../../component/mixin/ShopName"
import './style/home.less'
const FormItem = Form.Item;
/* 以类的方式创建一个组件 */
class Main extends Component {

    constructor(props) {
        super(props);
        this.columns = [{
            title: '学号',
            dataIndex: 'shop_id',
            width:'15%',
            render: (text, record, index) => this.renderColumns(this.state.data, index, 'shop_id', text),
        }, {
            title: '学生姓名',
            dataIndex: 'shop_name',
            width:'15%',
            render: (text, record, index) => this.renderColumns(this.state.data, index, 'shop_name', text),
        }, {
            title: 'mac地址',
            dataIndex: 'shop_address',
            width:'20%',
            render: (text, record, index) => this.renderColumns(this.state.data, index, 'shop_address', text),
        },{
            title:'部门',
            dataIndex:'shop_manager',
            width:'15%',
            render: (text, record, index) => this.renderColumns(this.state.data, index, 'shop_manager', text),
        }, {
            title: 'operation',
            dataIndex: 'operation',
            render: (text, record, index) => {
                // console.log("测试---》",text,"re-->", record,"in-->", index)
                const { editable } = this.state.data[index].shop_name;
                return (
                    <div className="editable-row-operations">
                        {
                            editable ?
                                <span>
                  <a onClick={() => this.editDone(index, 'save')}>保存</a>
                  <Popconfirm title="Sure to cancel?" onConfirm={() => this.editDone(index, 'cancel')}>
                    <a>取消</a>
                  </Popconfirm>
                </span>
                                :
                                <span>
                  <a onClick={() => this.edit(index)}>编辑</a>
                </span>
                        }
                    </div>
                );
            },
        }];
        this.state = {
            visible: false,
            loading: false,
            data: [{
                key: '0',
                shop_id: {
                    value: 2018211476
                },
                shop_name: {
                    editable: false,
                    value: '何鹏'
                },
                shop_address:{
                    editable: false,
                    value: 'xxxx'
                },
                shop_manager:{
                    editable: false,
                    value: '1'
                },
                shop_telephone:{
                    editable: false,
                    value:'18996720676'
                },
                avg_enter_ratio:{
                    value: 0.7
                },
                avg_stay_time:{
                    value: 30
                },
                total_enter_times:{
                    value: 	10000
                },
                shop_describe:{
                    editable: false,
                    value:'It\'s a descrpition'
                },
                viewDetail:{
                    value:'查看详情',
                },
                operation: {
                    value: '编辑',
                },
            }],
        };
    }
    renderColumns(data, index, key, text) {
        const { editable, status } = data[index][key];
        if (typeof editable === 'undefined') {
            return text;
        }
        return (<EditableCell
            editable={editable}
            value={text}
            onChange={value => this.handleChange(key, index, value)}
            status={status}
        />);
    }
    handleChange(key, index, value) {
        const { data } = this.state;
        data[index][key].value = value;
        this.setState({ data });
    }
    edit(index) {
        const { data } = this.state;
        Object.keys(data[index]).forEach((item) => {
            if (typeof data[index][item].editable !== 'undefined') {
                data[index][item].editable = true;
            }
        });
        this.setState({ data });
    }
    editDone(index, type) {
        // console.log("type-->",type)
        const { data } = this.state;
        Object.keys(data[index]).forEach((item) => {
            if (typeof data[index][item].editable !== 'undefined') {
                data[index][item].editable = false;
                data[index][item].status = type;
            }
        });
        this.setState({ data }, () => {
            Object.keys(data[index]).forEach((item) => {
                if (typeof data[index][item].editable !== 'undefined') {
                    delete data[index][item].status;
                }
            });
            // console.log("lgggg",data[index])
            if (type !== "cancel"){
                let shop_name = data[index].shop_name.value,
                    shop_addr = data[index].shop_address.value,
                    shop_manager = data[index].shop_manager.value,
                    shop_telephone = data[index].shop_id.value,
                    loginParams = {
                        shopName: shop_name,
                        shopAddr: shop_addr,
                        shopManager: shop_manager,
                        shopTelephone: shop_telephone
                    };
                // console.log("lo--->",loginParams)
                this.props.getData('updateShopInfo.action', loginParams, (res) => {
                    console.log("res-->",res);
                    if(res !== ""){
                        this.getShopInfos();
                    }
                })
            }
        });



    }
    getShopInfos = () => {
        let userName = localStorage.getItem("USERNAME"),
            loginParams = {
                userName: userName
            };
        this.props.getData('queryShopInfos.action', loginParams, (res) => {
            console.log("res-->",res);
            if(res !== ""){
                this.handleData(res)
            }
        })
    };
    handleData = (data) => {
        //data must be json array,[{"key": 1}, {"shop_name": "test"}]
        console.log(data);
        this.state.data = data.map((item) => {
            return {
                key: item.shop_id,
                shop_id:{
                    editable: false,
                    value: item.shop_id
                },
                shop_name: {
                    editable: false,
                    value: item.shop_name
                },
                shop_address:{
                    editable: false,
                    value: item.shop_addr
                },
                shop_manager:{
                    editable: false,
                    value: item.shop_manager
                },
                operation: {
                    value: '编辑',
                },
            }
        });
        console.log(this.state.data);
        this.setState({
            visible: false,
            data: this.state.data});
    };
    convertJsonToArray = (strData) => {
        strData = JSON.parse(strData);
        let arr=[];
        for(let p in strData){
            arr.push([p, strData[p]]);
        }
        return arr;
    };
    showModal = () => {
        this.setState({ visible: true });
    };
    handleCancel = () => {
        this.setState({ visible: false });
    };
    handleCreate = () => {
        const form = this.form;
        form.validateFields((err, values) => {
            if (!err) {
                let shop_name = values.shop_name,
                    user_name = localStorage.getItem("USERNAME"),
                    shop_addr = values.shop_addr,
                    shop_manager = values.shop_manager,
                    shop_telephone = values.shop_telephone,
                    shop_describe = values.shop_description,
                    loginParams = {
                        userName: user_name,
                        shopName: shop_name,
                        shopAddr: shop_addr,
                        shopManager: shop_manager,
                        shopTelephone: shop_telephone,
                        shopDescribe: shop_describe
                    };

                this.props.getData('addShopInfo.action', loginParams, (res) =>{
                    console.log(res);
                    if(res !== ""){
                        this.getShopInfos();
                    }
                }, 'addShop', 'GET');
                return;
            }
            console.log('Received values of form: ', values);
            form.resetFields();
            this.setState({ visible: false });
        });
    }
    saveFormRef = (form) => {
        this.form = form;
    }
    componentDidMount(){
        this.getShopInfos();
    }

    getExcel = () =>{
        this.setState({loading: true});
        let userName = localStorage.getItem("USERNAME"),
            params = {
                user_name: userName
            };
        this.props.getData("/exportExcel.action", params, (res) => {
            if(res.success == 1){
                message.success("成功导出到目录：/Users/maicius/Desktop/");
            }
            this.setState({loading: false});
        })
    };

    render() {
        const { data } = this.state;
        const dataSource = data.map((item) => {
            const obj = {};
            Object.keys(item).forEach((key) => {
                obj[key] = key === 'key' ? item[key] : item[key].value;
            });
            return obj;
        });
        const columns = this.columns;

        return (
            <div>
                <Bcrumb title="学员管理"/>
                <Table bordered dataSource={dataSource} columns={columns}
                       expandedRowRender={record => <p>{record.shop_describe}</p>}/>
                <div>
                    <Button type="primary" onClick={this.showModal}>添加学员</Button>
                    <CollectionCreateForm ref={this.saveFormRef}
                                          visible={this.state.visible}
                                          onCancel={this.handleCancel}
                                          onCreate={this.handleCreate}/>
                    {/*<Button className="mg-left10" type="primary" loading={this.state.loading} onClick={this.getExcel}>导出报表</Button>*/}
                </div>
            </div>
        );
    }
}



Main.contextTypes = {
};

export default RenderData({
    id: 'twoui', // 应用关联使用的redex
    component: Main // 接收数据的组件入口
});