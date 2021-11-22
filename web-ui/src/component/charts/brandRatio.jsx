
import React, {Component} from 'react';
import {is, fromJS} from 'immutable';
import echarts from 'echarts/lib/echarts'
import 'echarts/lib/chart/pie';
// 标题插件
import 'echarts/lib/component/title';
import 'echarts/lib/component/tooltip';
import 'echarts/lib/component/dataZoom';
import 'echarts/lib/component/graphic';
import 'echarts/lib/component/grid';
let backColor = '#404a59';
class BrandRatio extends Component{
    constructor(props) {
        super(props);
        this.state = {
            myChart: null
        }
    }
    shouldComponentUpdate(nextProps, nextState){
        return !is(fromJS(this.props), fromJS(nextProps)) || !is(fromJS(this.state), fromJS(nextState))
    }

    componentDidMount(){
        this.initPicture()
    }
    componentDidUpdate(){
        this.updateOption()
    }
    initPicture(){
        const that = this;
        const {data} = this.props;
        let myChart = echarts.init(that.lineChart);
        that.setState({
            myChart:myChart
        });
        let options = that.setLineOption(data);
        let update = that.updateOption;
        myChart.setOption(options);

        window.addEventListener('resize', function () {
            that.updateOption;
        });
        myChart.on('dataZoom', function () {
            that.updateOption;
        })
    }
    updateOption = () => {
        const {data} = this.props;
        const {myChart} = this.state;
        let options = this.setLineOption(data);
        myChart.setOption(options);
    };
    setLineOption = (data) =>{
        return {
            backgroundColor: backColor,
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            title: {
                text: '高峰期',
                left: 'center',
                top: 20,
                textStyle: {
                    color: '#ccc'
                }
            },

            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },

            visualMap: {
                show: false,
                min: 80,
                max: 600,
                inRange: {
                    colorLightness: [0, 1]
                }
            },
            series : [
                {
                    name:'高峰期',
                    type:'pie',
                    radius : '55%',
                    center: ['50%', '50%'],
                    data:[
                        {value:335, name:'9-11点'},
                        {value:310, name:'11-14点'},
                        {value:274, name:'14-18点'},
                        {value:235, name:'18-19点'},
                        {value:400, name:'19-22点'}
                    ].sort(function (a, b) { return a.value - b.value; }),
                    roseType: 'area',
                    label: {
                        normal: {
                            textStyle: {
                                color: 'rgba(255, 255, 255, 0.3)'
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            lineStyle: {
                                color: 'rgba(255, 255, 255, 0.3)'
                            },
                            smooth: 0.2,
                            length: 10,
                            length2: 20
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: '#00BFFF',
                            shadowBlur: 200,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    },

                    animationType: 'scale',
                    animationEasing: 'elasticOut',
                    animationDelay: function (idx) {
                        return Math.random() * 200;
                    }
                }
            ]
        };
    };

    render() {
        return (
            <div ref={ref=>{this.lineChart = ref}} style={{width:"100%", height: "400px"}}/>
        );
    }
}
export default BrandRatio;