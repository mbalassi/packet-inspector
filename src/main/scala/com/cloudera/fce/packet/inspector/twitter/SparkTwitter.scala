package com.cloudera.fce.packet.inspector.twitter

import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

import org.apache.spark.streaming.twitter._
import com.cloudera.fce.packet.inspector.utils.SparkUtils.deleteDir


object SparkTwitter {
  def main(args: Array[String]) {

    val conf = new SparkConf(true)//.setMaster("local[4]")
        .setAppName("SparkTwitter")

    val outdir = "hdfs://kudu-mk-1.vpc.cloudera.com:8020/tmp/spark-twitter"
//    val outdir = "file:///tmp/spark-twitter"

    System.setProperty("twitter4j.oauth.consumerKey", args(0))
    System.setProperty("twitter4j.oauth.consumerSecret", args(1))
    System.setProperty("twitter4j.oauth.accessToken", args(2))
    System.setProperty("twitter4j.oauth.accessTokenSecret", args(3))

    deleteDir(outdir)

    val sc = new SparkContext(conf)

    val ssc = new StreamingContext(sc, Seconds(3))

    val rawInput = TwitterUtils.createStream(ssc, None)

    rawInput.saveAsTextFiles(outdir)
    ssc.start
    ssc.awaitTermination
  }
}
