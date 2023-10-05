package com.ceva.cfastbi.transcation.exception;

public enum SaveRecordExceptionCodes {

  FTP_CONN_FAILED(101, "FTP Connection Failed"), FTP_ACTIVITY_FAILURE(102,
      "FTP Activitity Failed"), FILE_RETRIEVE_FAILURE(103,
          "Unable to retrieve file"), CLOSE_INPUT_STREAM_FTP(104,
              "Unable to retrieve file"), CASSANDRA_DB_CONN_FAILED(201,
                  "Cassandra Connection Failed"), POSTGRES_DB_CONN_FAILED(301,
                      "Postgres Connection Failed"), PARSE_OFSXML_FAILED(401,
                          "Unable to parse Ofs Booking XML");

  private final int id;
  private final String msg;

  SaveRecordExceptionCodes(int id, String msg) {
    this.id = id;
    this.msg = msg;
  }

  public int getId() {
    return this.id;
  }

  public String getMsg() {
    return this.msg;
  }
}
