package bigpanda

import java.io.{BufferedReader, InputStream, InputStreamReader}

/**
  * Created by orip on 2/26/2016.
  */
class FileInputConsumer(path: String) extends InputConsumer {
  private val processBuilder = new ProcessBuilder(path)
  private val process: Process = processBuilder.start()
  private val stream: InputStream = process.getInputStream
  private val reader = new BufferedReader(new InputStreamReader(stream))

  override def getLine: String = reader.readLine()

  override def close(): Unit = {
    reader.close()
    stream.close()
    process.destroy()
  }
}
