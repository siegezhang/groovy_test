package groovlets

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.ServletContextHandler

//import org.eclipse.jetty.server.Server
//import org.eclipse.jetty.servlet.ServletContextHandler
//
//def method = request.method
//
//if (!session) {
//    session = request.getSession(true)
//}
//
//if (!session.groovlet) {
//    session.groovlet = 'Groovlets rock!'
//}
//
//html.html {
//    head {
//        title 'Groovlet info'
//    }
//    body {
//        h1 'General info'
//        ul {
//            li "Method: ${method}"
//            li "RequestURI: ${request.requestURI}"
//            li "session.groovlet: ${session.groovlet}"
//            li "application.version: ${context.version}"
//        }
//
//        h1 'Headers'
//        ul {
//            headers.each {
//                li "${it.key} = ${it.value}"
//            }
//        }
//    }
//}

//
//def startJetty() {
//    def jetty = new Server(9090)
//
//    def context = new ServletContextHandler.Context(jetty, '/', Context.SESSIONS)  // Allow sessions.
//    context.resourceBase = '.'  // Look in current dir for Groovy scripts.
//    context.addServlet(GroovyServlet, '*.groovy')  // All files ending with .groovy will be served.
//    context.setAttribute('version', '1.0')  // Set an context attribute.
//
//    jetty.start()
//}
//
//println "Starting Jetty, press Ctrl+C to stop."
//startJetty()
