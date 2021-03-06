import React, { Component} from 'react'; // 引入了React和PropTypes
import echarts from 'echarts';
import * as ligquid from 'echarts-liquidfill'
import pureRender from 'pure-render-decorator';
import { is, fromJS} from 'immutable';
import { Router, Route, IndexRoute, browserHistory, History, Link } from 'react-router';
import { connect } from 'react-redux';
import { RenderData } from '../../component/mixin';
import Websocket from 'react-websocket';
// 公共面包屑
import { Bcrumb } from '../../component/bcrumb/bcrumb';
import styles from './style/home.less';
// import Singleton from 'js/socket';
import { Icon, Row, Col, Card, Table } from 'antd';
let userDiagramDom;
let enterRate, checkInRatio, deepAccessRatio, jumpRatio;
let bounceRateDom;
let webSocket = null;
let backColor = 'rgba(0,0,0,.1)';
/* 以类的方式创建一个组件 */
class Main extends Component {
    constructor(props) {
    	super(props);
        this.state = {
             current: 0,
             userDiagramData:[],
             checkInFlows:0,
             totalFlow:0,
             checkInFlow:[0],
             checkInRatio: 0,
             avgCheckInRatio: 0,
             deepAccessRatio: 0,
             jumpRatio:0,
            checkInRate:0,
            deepVisitRatio:0,
        };
    }


    autoResize() {
        let userDiagram = document.getElementById('user-diagram');
        userDiagram.style.width = '100%';
        userDiagram.style.height = '500px';

        let enterRatio = document.getElementById('enter-ratio');
        enterRatio.style.width = '100%';
        enterRatio.style.height = '300px';

        let JumpRatio = document.getElementById('jump-ratio');
        JumpRatio.style.width = '100%';
        JumpRatio.style.height = '300px';
    }

    getLocalTime = (nS) => {
        console.log(new Date(parseInt(nS)).toLocaleString());
        return new Date(parseInt(nS)).toLocaleString();
    }
    handleUserData(data) {
        // let obj = eval('(' + data + ')');
        console.log("hadleuserdate----->"+data);
        data = JSON.parse(data);
        console.log("data---->"+data.toString());
        let newData = [this.getLocalTime(data.time), data.checkInRatio, data.jumpRatio, data.deepVisitRatio];
        this.state.totalFlow = data.totalFlow;
        this.state.checkInFlows = data.checkInFlow;

        this.state.checkInRate = data.checkInRate;

        this.state.avgCheckInRatio = (this.state.checkInFlows / this.state.totalFlow).toFixed(2);

        this.state.userDiagramData.push(newData);
        this.state.checkInRatio = data.checkInRatio.toFixed(0);
        this.state.deepAccessRatio = data.deepVisitRatio;
        this.state.jumpRatio = data.jumpRatio.toFixed(0);
        this.setState({ userDiagramData: this.state.userDiagramData,
                        checkInRatio:this.state.checkInRatio,
                        deepAccessRatio: this.state.deepAccessRatio,
                        jumpRatio: this.state.jumpRatio,

                        checkInFlows: this.state.checkInFlows,
                        avgCheckInRatio: this.state.avgCheckInRatio,

                        totalFlow: this.state.totalFlow,
                        checkInRate: this.state.checkInRate,
            deepVisitRatio: this.state.deepVisitRatio

            });
        console.log("userDiagramData:" + this.state.userDiagramData);
    }

    drawCheckInRatio(){
       // console.log("draw check in ratio");
        let option = {
            title: {
                text: '内卷率',
                textStyle:{
                    color: '#fff'
                }
            },
            backgroundColor: backColor,
            series: [{
                type: 'liquidFill',
                radius: '80%',
                data: [(this.state.checkInFlows/108).toFixed(2), {
                    value: (this.state.checkInFlows/108).toFixed(2)-0.1,
                    direction: 'left',
                    itemStyle: {
                        normal: {
                            color: 'red'
                        }
                    }
                }, {value:(this.state.checkInFlows/108).toFixed(2)-0.2,
                    direction: 'left',
                    itemStyle:{
                    normal:{
                        color: 'green'
                        }
                    }}, (this.state.checkInFlows/108).toFixed(2)-0.3]
            }]
        };
        checkInRatio.setOption(option);
    }

    // drawDeepAccessRatio(){
    //     let option = {
    //         title: {
    //             text: '深访率',
    //             textStyle:{
    //                 color: '#fff'
    //             }
    //         },
    //         backgroundColor: backColor,
    //         series: [{
    //             type: 'liquidFill',
    //             radius: '80%',
    //             data: [{value:this.state.deepAccessRatio,
    //                     direction: 'right',
    //                     itemStyle:{
    //                     normal:{
    //                         color: 'blue'
    //                     }
    //                 }}, {
    //                 value: this.state.deepAccessRatio-0.1,
    //                 direction: 'left',
    //                 itemStyle: {
    //                     normal: {
    //                         color: 'red'
    //                     }
    //                 }
    //             }, {value:this.state.deepAccessRatio-0.2,
    //                 direction: 'right',
    //                 itemStyle:{
    //                     normal:{
    //                         color: 'blue'
    //                     }
    //                 }}, this.state.deepAccessRatio-0.3]
    //         }]
    //     };
    //     deepAccessRatio.setOption(option);
    // }

    drawJumpRatio(){
        console.log("draw deep access");
        let option = {
            title: {
                text: '躺平率',
                textStyle:{
                    color: '#fff'
                }
            },
            backgroundColor: backColor,
            series: [{
                type: 'liquidFill',
                radius: '80%',
                data: [{value: (1-this.state.checkInFlows/108).toFixed(2),
                        direction: 'left'}, {
                    value: (1-this.state.checkInFlows/108).toFixed(2)+0.1,
                    direction: 'left',
                    itemStye: {
                        normal: {
                            color: 'red'
                        }
                    }
                }, (1-this.state.checkInFlows/108).toFixed(2)-0.3, (1-this.state.checkInFlows/108).toFixed(2)-0.4]
            }]
        };
        jumpRatio.setOption(option);
    }

    drawUserDiagram(){
        // 绘制图表
        //console.log("draw user diagram");
        try {
            userDiagramDom.setOption({
                title: {
                    text: '实时流量',
                    left: 'left',
                    textStyle: {
                        color: '#fff'
                    }
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                legend: {
                    y: 'top',
                    data: ['一部', '二部','三部'],
                    textStyle: {
                        color: '#fff',
                        fontSize: 16
                    }
                },
                backgroundColor: backColor,
                //鼠标浮动
                tooltip: {
                    trigger: 'axis'
                },
                xAxis: {
                    data: this.state.userDiagramData.map(function (item) {
                        return item[0];
                    }),
                    axisLine: {
                        lineStyle: {
                            color: 'white',
                            width: 2
                        }
                    }
                },
                yAxis: {
                    splitLine: {
                        show: false
                    },
                    axisLine: {
                        lineStyle: {
                            color: 'white',
                            width:2
                        }
                    }
                },
                dataZoom: [{
                    startValue: '2017-06-01',
                    textStyle: {
                        color: '#8392A5'
                    },
                    dataBackground: {
                        areaStyle: {
                            color: '#8392A5'
                        },
                        lineStyle: {
                            opacity: 0.8,
                            color: '#8392A5'
                        }
                    },
                }, {
                    type: 'inside'
                }],
                visualMap: {
                    top: 10,
                    right: 10,
                    textStyle: {
                        color: '#fff'
                    },
                    pieces: [{
                        gt: 0,
                        lte: 5,
                        color: '#096'
                    }, {
                        gt: 5,
                        lte: 10,
                        color: '#ffde33'
                    }, {
                        gt: 10,
                        lte: 30,
                        color: '#ff9933'
                    }, {
                        gt: 30,
                        lte: 60,
                        color: '#cc0033'
                    }, {
                        gt: 60,
                        lte: 90,
                        color: '#660099'
                    }, {
                        gt: 90,
                        color: '#7e0023'
                    }],
                    outOfRange: {
                        color: '#999'
                    }
                },
                series: [
                    {
                    name: '一部',
                    left: 'center',
                    type: 'line',
                    data: this.state.userDiagramData.map(function (item) {
                        return item[1];
                    }),
                    markLine: {
                        silent: true,
                        label: '一部',
                        lineStyle: {
                            normal: {
                                width: 3,
                                type: 'dashed'
                            }
                        },
                        data: [{
                            yAxis: 50
                        }, {
                            yAxis: 100
                        }, {
                            yAxis: 150
                        }, {
                            yAxis: 200
                        }, {
                            yAxis: 300
                        }]
                    }
                }, {
                    name: '二部',
                    left: 'center',
                    type: 'line',
                    data: this.state.userDiagramData.map(function (item) {
                        return item[2];
                    }),
                    markLine: {
                        silent: true,
                        label: '二部',
                        lineStyle: {
                            normal: {
                                width: 3,
                                type: 'dashed'
                            }
                        },
                        data: [{
                            yAxis: 50
                        }, {
                            yAxis: 100
                        }, {
                            yAxis: 150
                        }, {
                            yAxis: 200
                        }, {
                            yAxis: 300
                        }]
                    }
                },{
                        name: '三部',
                        left: 'center',
                        type: 'line',
                        data: this.state.userDiagramData.map(function (item) {

                            console.log("item----->",item)
                            return item[3];
                        }),
                        markLine: {
                            silent: true,
                            label: '三部',
                            lineStyle: {
                                normal: {
                                    width: 3,
                                    type: 'dashed'
                                }
                            },
                            data: [{
                                yAxis: 50
                            }, {
                                yAxis: 100
                            }, {
                                yAxis: 150
                            }, {
                                yAxis: 200
                            }, {
                                yAxis: 300
                            }]
                        }
                    }]

            });
        }catch (e){
            alert("error in get data");
        }
        window.onresize = function () {
            this.autoResize();
            userDiagramDom.resize();
            checkInRatio.resize();
            jumpRatio.resize();
            deepAccessRatio.resize();
        }.bind(this);
    }

    componentWillMount(){
        // this.socket.on('testChannel', function (msg) {
        //     console.log(msg)
        // })
    }
    componentDidMount() {
        this.autoResize();
        //console.log("componentDidMount");
        userDiagramDom = echarts.init(document.getElementById('user-diagram'));
        checkInRatio = echarts.init(document.getElementById('enter-ratio'));
        //deepAccessRatio = echarts.init(document.getElementById('deep-access-ratio'));
        jumpRatio = echarts.init(document.getElementById('jump-ratio'));
        //console.log("dom initial");
        this.drawUserDiagram();
        this.drawCheckInRatio();
        //this.drawDeepAccessRatio();
        this.drawJumpRatio();
        //console.log("echarts finish");
        if ('WebSocket' in window) {
            //webSocket = new WebSocket("ws://116.62.41.211:8888/WIFIProbeAnalysis_web-1.0-SNAPSHOT/websocket");
            //webSocket = new WebSocket("ws://120.24.238.195:8080/WIFIProbeAnalysis_web-1.0-SNAPSHOT/websocket") ;
            webSocket = new WebSocket("ws://localhost:8080/websocket");
            webSocket.onerror = () =>{
            };
            webSocket.onopen = () =>{
                console.log("webSocket open");
            };
            //监听message事件，在服务器响应时接受数据。返回的数据存储在事件对象中。
            webSocket.onmessage = (data) =>{
                console.log("onmessage-->"+data.data)
                this.handleUserData(data.data);
            };
            window.onbeforeunload = function () {
                webSocket.close();
            }
        }
        else {
            alert('当前浏览器 Not support websocket')
        }
    }
    componentDidUpdate() {
        this.drawUserDiagram();
        this.drawCheckInRatio();
        //this.drawDeepAccessRatio();
        this.drawJumpRatio();
    }

	render() {
        const columns = [
            {title:'属性名称', dataIndex:'property_name'},
            {title:'值',dataIndex: 'property_value',sorter:(a, b) => a.property_value - b.property_value}
        ];
        const dataSource = [
            {key:'1',property_name: '双体区域内总人数', property_value: this.state.checkInFlows},
            {key:'2',property_name: '一部人数', property_value: this.state.checkInRatio},
            {key:'3',property_name: '二部人数', property_value: this.state.jumpRatio},
            {key:'4',property_name: '三部人数', property_value: this.state.deepAccessRatio}];
		return (
        <div className="home-container mg-top20">
            <Row>
            	<Col span={24}>
                    <Col span={8}>
                        <Card title="进入率" id="enter-ratio"/>
                    </Col>
                    <Col span={8}>
                        <Table label="数据一览表"
                               columns={columns}
                               dataSource={dataSource}
                               bordered
                               class="uniform-background">

                        </Table>
                    </Col>
                    <Col span={8}>
                        <Card title="跳出率" id="jump-ratio"/>
                    </Col>
                    <Col span={24} className="">
                        <Card title="用户" id="user-diagram"/>
                    </Col>
                </Col>
            </Row>
        </div>	
		);
	}
}

export default RenderData({
    id: 'home',  //应用关联使用的redux
    component: Main //接收数据的组件入口
});