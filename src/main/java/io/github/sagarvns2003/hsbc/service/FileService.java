package io.github.sagarvns2003.hsbc.service;

import java.io.*;

public class FileService {
  public void reverseFileContent(InputStream inputStream, OutputStream outputStream)
      throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    StringBuilder inputContent = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      inputContent.append(line);
    }
    String reversedContent = inputContent.reverse().toString();
    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
    writer.write(reversedContent);
    writer.flush();
  }
}
