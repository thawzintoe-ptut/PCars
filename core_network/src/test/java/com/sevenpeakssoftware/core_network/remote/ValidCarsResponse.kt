package com.sevenpeakssoftware.core_network.remote

val validArticlesResponse = """
   {
    "status": "success",
    "content": [{
            "id": 1,
            "title": "Thaw",
            "dateTime": "25.05.2018 14:13",
            "tags": [],
            "content": [{
                "type": "text",
                "subject": "Q7",
                "description": "test description"
            }],
            "ingress": "test ingress",
            "image": "audi_q7.jpg",
            "created": 1511968425,
            "changed": 1534311497
            }
        ],
    "serverTime": 1668732951
    }
""".trimIndent()