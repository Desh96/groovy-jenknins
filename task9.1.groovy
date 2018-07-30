def nexus_path = "192.168.1.2:8081"
def group = "project"
def artifact = "helloworld"
def version = "1.${BUILD_NUMBER}"
def repo = "project-releases"
def com = "push" 
def log_pass = "admin:admin123"


if("$com"=="push"){
    def File = new File ("/tmp/${artifact}-${version}.tar.gz").getBytes()
    def con = new URL("http://${nexus_path}/repository/${repo}/${group}/${artifact}/${version}/${artifact}-${version}.tar.gz").openConnection()
    def authString = "admin:admin123".getBytes().encodeBase64().toString()
    con.setRequestProperty( "Authorization", "Basic ${authString}")
    con.setRequestMethod("PUT")
    con.doOutput = true
    con.setRequestProperty( "Content-Type", "application/x-gzip" )
    def writer = new DataOutputStream(con.outputStream)
    writer.write(File)
    writer.close()
    println con.responseCode
}

else {
    new File("/tmp/${ART_ID}-${version}.tar.gz").withOutputStream { out ->
        def url = new URL("http://${nexus_path}/repository/${repo}/${group}/${artifact}/${VER}/${artifact}-${version}.tar.gz").openConnection()
        out << url.inputStream
    }
}
