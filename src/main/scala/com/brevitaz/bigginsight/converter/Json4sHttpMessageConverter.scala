package com.brevitaz.bigginsight.converter

import java.io.{IOException, InputStreamReader, OutputStreamWriter, Reader}
import java.lang.reflect.Type
import java.nio.charset.Charset

import com.google.gson.reflect.TypeToken
import org.springframework.http._
import org.springframework.http.converter.{AbstractGenericHttpMessageConverter, HttpMessageNotReadableException}

class Json4sHttpMessageConverter extends AbstractGenericHttpMessageConverter[Object](MediaType.APPLICATION_JSON_UTF8, new MediaType("application", "*+json", Charset.forName("UTF-8"))) {

  val DEFAULT_CHARSET: Charset = Charset.forName("UTF-8")

  override def writeInternal(t: Object, `type`: Type, outputMessage: HttpOutputMessage): Unit = {
    import org.json4s._
    import org.json4s.native.Serialization
    implicit val formats = Serialization.formats(NoTypeHints)

    outputMessage.getBody
    val charset: Charset =  getCharset(outputMessage.getHeaders)
    val writer: OutputStreamWriter = new OutputStreamWriter(outputMessage.getBody, charset)
    writer.write(org.json4s.native.Serialization.write(t))
    writer.close()
  }

  private def getCharset(headers: HttpHeaders): Charset = {
    if (headers == null || headers.getContentType == null || headers.getContentType.getCharSet == null) {
      return DEFAULT_CHARSET
    }
     headers.getContentType.getCharSet
  }

  @throws(classOf[IOException])
  @throws(classOf[HttpMessageNotReadableException])
  def read(`type`: Type, contextClass: Class[_], inputMessage: HttpInputMessage): AnyRef = {
    val token: TypeToken[_] = getTypeToken(`type`)
     readTypeToken(token, inputMessage)
  }

  @throws(classOf[IOException])
  private def readTypeToken(token: TypeToken[_], inputMessage: HttpInputMessage): AnyRef = {
    val json: Reader = new InputStreamReader(inputMessage.getBody, getCharset(inputMessage.getHeaders))

    import org.json4s._
    import org.json4s.native.Serialization
    implicit val formats = Serialization.formats(NoTypeHints)
    val c = Class.forName(token.getType.getTypeName)

    org.json4s.jackson.JsonMethods.parse(json).extract(formats,Manifest.classType(c))

  }

  protected def getTypeToken(`type`: Type): TypeToken[_] = {
     TypeToken.get(`type`)
  }

  override def canRead(clazz: Class[_], mediaType: MediaType): Boolean = {
     canRead(mediaType)
  }

  override def canWrite(clazz: Class[_], mediaType: MediaType): Boolean = {
     canWrite(mediaType)
  }

  protected def supports(clazz: Class[_]): Boolean = {
    throw new UnsupportedOperationException
  }

  @throws(classOf[IOException])
  @throws(classOf[HttpMessageNotReadableException])
  override def readInternal(clazz: Class[_ <: Object], inputMessage: HttpInputMessage): Object = {
    val token: TypeToken[_] = getTypeToken(clazz)
    readTypeToken(token, inputMessage)
  }

}
