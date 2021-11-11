package edu.cs.scu.analysis

import edu.cs.scu.analysis.offlineanalysis.GetStayTime
import edu.cs.scu.analysis.realtimeanalysis.RealTimeAnalysis
import edu.cs.scu.common.constants.AnalysisConstants
import edu.cs.scu.scalautils.InitUtil
import org.apache.spark.streaming.StreamingContext

import java.util.concurrent.{ExecutorService, Executors}


/**
  * 分析程序入口
  *
  * Created by Wang Han on 2017/3/29 14:42.
  * E-mail address is wanghan0501@vip.qq.com.
  * Copyright © Wang Han SCU. All Rights Reserved.
  *
  * @author Wang Han
  */
object Main {
  def main(args: Array[String]): Unit = {

    val spark = InitUtil.initSparkSession()
    // 流环境
    val streamingContext: StreamingContext = InitUtil.getStreamingContext(spark.sparkContext)

    val threadPool: ExecutorService = Executors.newFixedThreadPool(AnalysisConstants.THREADS_NUM)
    try {
      //redis数据
      threadPool.execute(new GetStayTime)
      //threadPool.execute(new OfflineMain)
    } finally {
      threadPool.shutdown()
    }

    // 从kafka中读取流数据
//    val kafkaData = InitUtil.getDStreamFromKafka(streamingContext).map(_._2)
//
//    // 如果读入的数据不为空
//    if (kafkaData != null) {
//      println("get data from kafka")
//      RealTimeAnalysis.analysis(spark, streamingContext, kafkaData)
//    }

    // 从hdfs中读取流数据
    val originData = InitUtil.getDStream(streamingContext)
    // 如果读入的数据不为空
    if (originData != null) {
      println("get data from hdfs")
      RealTimeAnalysis.analysis(spark, streamingContext, originData)
    }
    // 启动流环境
    streamingContext.start()
    streamingContext.awaitTermination()

  }
}
