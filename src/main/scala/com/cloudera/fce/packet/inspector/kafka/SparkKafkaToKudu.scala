//package com.cloudera.fce.packet.inspector.kafka
//
//import com.cloudera.fce.packet.inspector.PCAP
//import com.cloudera.fce.packet.inspector.PCAPJsonProtocol._
//import kafka.serializer.StringDecoder
//import org.apache.flink.api.java.utils.ParameterTool
//import org.apache.spark.streaming.{Seconds, StreamingContext}
//import org.apache.spark.{SparkConf, SparkContext}
//import org.apache.spark.streaming.kafka._
//import org.apache.spark.storage.StorageLevel
//import org.codehaus.jackson.JsonParser.Feature
//import org.codehaus.jackson.map.ObjectMapper
//import spray.json._
//
//
//object SparkKafkaToKudu {
//  def main(args: Array[String]) {
//
//      val params = ParameterTool.fromArgs(args)
//      //    val master = params.getRequired("master")
//      val window = params.getInt("window", 3)
//
//      val conf = new SparkConf(true)//.setMaster(master)
//        .setAppName("SparkKafkaToKudu")
//
//      val sc = new SparkContext(conf)
//
//      val ssc = new StreamingContext(sc, Seconds(window))
//
//    val rawInput = KafkaUtils.createStream[String, String, StringDecoder, StringDecoder](ssc,
//      Map("auto.offset.reset" -> "smallest",
//        "group.id" -> "data-science-3",
//        "zookeeper.connect" ->
//        "kudu-mk-1.vpc.cloudera.com:2181/kafka"),
//      Map("avro-pcap" -> 1), StorageLevel.MEMORY_AND_DISK_SER_2)
//
//    val parsedInput = rawInput.flatMap{ case (key, value) =>
//      val reader = new ObjectMapper()
//        .configure(Feature.ALLOW_SINGLE_QUOTES, true)
//        .reader()
//
//      try {
//        val node = reader.readTree(value)
//        List(node.toString.parseJson.convertTo[PCAP])
//      } catch {
//        case e: Exception => List()
//      }
//    }
//
//    parsedInput.print
//    ssc.start
//    ssc.awaitTermination
//  }
//}
