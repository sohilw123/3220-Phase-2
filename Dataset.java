import java.io.*;
import java.util.Arrays;

public class Dataset {
  private String name;
  private String description;
  private String[] fields;
  private String source;
  // private Frequency updateFrequency;

  // Constructor
  public Dataset(String name, String description, String[] fields, String source) {
    this.name = name;
    this.description = description;
    this.fields = fields;
    this.source = source;
    // this.updateFrequency = updateFrequency;
  }

  // Method to parse dataset from CSV format
  public static Dataset fromCSV(String csvLine) {
    String[] data = csvLine.split(",");
    String name = data[0];
    String description = data[1];
    String[] fields = data[2].split("\\|");
    String source = data[3];
    // Frequency updateFrequency = Frequency.valueOf(data[4]);
    return new Dataset(name, description, fields, source);
  }

  // Method to convert dataset to CSV format
  public String toCSV() {
    StringBuilder sb = new StringBuilder();
    sb.append(name).append(",");
    sb.append(description).append(",");
    sb.append(String.join("|", fields)).append(",");
    sb.append(source).append(",");
    sb.append("placeholder");
    return sb.toString();
  }

  // Getter and setter methods
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String[] getFields() {
    return fields;
  }

  public void setFields(String[] fields) {
    this.fields = fields;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

//  public Frequency getUpdateFrequency() {
//    return updateFrequency;
//  }
//
//  public void setUpdateFrequency(Frequency updateFrequency) {
//    this.updateFrequency = updateFrequency;
//  }
}
