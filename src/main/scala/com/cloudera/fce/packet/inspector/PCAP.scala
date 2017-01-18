//package com.cloudera.fce.packet.inspector
//
//import org.codehaus.jackson.JsonParser.Feature
//import org.codehaus.jackson.map.ObjectMapper
//import spray.json._
//
//case class PCAP(dstIP: String, dumpHost: String, srcPort: Int, dumpDevice: String, srcIP: String,
//                dstPort: Int, data: String, seqNumber: Long, packet_grab_nr: Long, ts: Long,
//                protocolType: String, ackNumber: Long) {
//}
//
//object PCAPJsonProtocol extends DefaultJsonProtocol {
//  implicit val pcapFormat = jsonFormat12(PCAP)
//}
//
//object PCAPJsonTest {
//
//  import PCAPJsonProtocol._
//
//  def main(args: Array[String]): Unit = {
//    val data = "{'dstIP': '172.28.209.73', 'dumpHost': 'kudu-mk-2.vpc.cloudera.com', " +
//      "'srcPort': 48171, 'dumpDevice': 'eth0', 'srcIP': '172.28.211.45', 'dstPort': 7051, " +
//      "'data': \"48171|7051|1605638324|562840347|(,j0'2(kudu.master.MasterServicex0bTSHeartbeatPu8-+ " +
//      "ef91831092ae4a018b222ad219ca725d '\", 'seqNumber': 0, 'packet_grab_nr': 0, 'ts': 0, 'protocolType': " +
//      "'TCP', 'ackNumber': 0}"
//
//    val reader = new ObjectMapper()
//      .configure(Feature.ALLOW_SINGLE_QUOTES, true)
//      .reader()
//
//    val node = reader.readTree(data)
//
//    println(data)
//    println(node.toString.parseJson.convertTo[PCAP])
//  }
//}