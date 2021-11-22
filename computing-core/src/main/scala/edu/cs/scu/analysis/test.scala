//package edu.cs.scu.analysis
//
//import edu.cs.scu.analysis.realtimeanalysis.RealTimeAnalysis.map
//import edu.cs.scu.scalautils.InitUtil
//import org.apache.spark.sql.DataFrame
//
//import scala.collection.mutable
//
//object test {
//  val map=mutable.Map[String,String]()
//  val map2: Map[String, String] = Map[String, String](
//    elems = "url" -> "jdbc:mysql://localhost:3306/WIFIProbeAnalysis?characterEncoding=UTF-8&useSSL=false",
//    "driver" -> "com.mysql.jdbc.Driver",
//    "user" -> "root",
//    "password" -> "root",
//    "dbtable" -> "shop_info"
//  )
//
//  def main(args: Array[String]): Unit = {
//    val spark = InitUtil.initSparkSession()
//    val frame: DataFrame = spark
//      .read
//      .format("jdbc")
//      .options(
//        map2
//      )
//      .load
//
//    frame.rdd.foreach{
//      x=>{
//        val st: Array[String] = x.toString()
//          .replaceAll("\\[","")
//          .replaceAll("\\]","")
//          .split(",")
//        map+=st(1)->st(3)
//      }
//    }
//
////    for((key,value) <- map){
////      println(key,value)
////    }
//    println(map.get("a6:c5:04:4e:f9:7b"))
////    println(map("e3:64:ed:ef:6f:6c"))
////    println(map("e3:64:ed:ef:6f:6c"))
//  }
//
//
//}
