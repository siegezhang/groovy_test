package http

def html = "http://www.baidu.com".toURL().text

println html

String getResult
((HttpURLConnection) 'http://www.zhihu.com/hot'.toURL().openConnection()).with({
    requestMethod = 'GET'
    doOutput = true
    setRequestProperty('Content-Type', 'json') // Set your content type.
    outputStream.withPrintWriter({ printWriter ->
        printWriter.write('...') // Your post data. Could also use withWriter() if you don't want to write a String.
    })
    // Can check 'responseCode' here if you like.
    getResult = inputStream.text // Using 'inputStream.text' because 'content' will throw an exception when empty.
    println getResult
})