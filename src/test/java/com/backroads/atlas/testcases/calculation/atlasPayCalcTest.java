package com.backroads.atlas.testcases.calculation;

import java.io.*;
public class atlasPayCalcTest {

  public static void main(String argv[]) {
    try {
      String line;
      Process p = Runtime.getRuntime().exec
        ("sqlcmd -S ibis6 -U appuser -P appuser\n"
        		+ ":r \"C:\\DatabaseTest\\dbtest\\src\\com\\backroads\\adtest\\sql\\All - RUN.sql\"\n");
      BufferedReader input =
        new BufferedReader
          (new InputStreamReader(p.getInputStream()));
      while ((line = input.readLine()) != null) {
        System.out.println(line);
      }
      input.close();
    }
    catch (Exception err) {
      err.printStackTrace();
    }
  }
}