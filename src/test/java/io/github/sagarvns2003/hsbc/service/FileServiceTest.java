package io.github.sagarvns2003.hsbc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

class FileServiceTest {

  @Test
  void testReverseFileContents() throws IOException {
    String inputContent = "ABCDE";
    String expectedOutput = "EDCBA";
    ByteArrayInputStream inputStream =
        new ByteArrayInputStream(inputContent.getBytes(StandardCharsets.UTF_8));
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    FileService fileService = new FileService();
    fileService.reverseFileContent(inputStream, outputStream);
    String actualOutput = outputStream.toString(StandardCharsets.UTF_8);
    assertEquals(expectedOutput, actualOutput);
  }
}
