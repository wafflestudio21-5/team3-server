package com.everywaffle.team3server

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class TestPageController {
    @GetMapping("/test-page")
    @ResponseBody
    fun showTestPage(): String {
        return """
            <html>
                <head>
                    <title>Test Page</title>
                </head>
                <body>
                    <h1>This is a Test Page</h1>
                    <p>Welcome to the test page of Team3 Server.</p>
                </body>
            </html>
        """.trimIndent()
    }
}