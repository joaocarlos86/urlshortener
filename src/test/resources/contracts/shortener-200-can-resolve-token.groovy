package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "Allows resolving one Short URL"
    request{
        method GET()
        url("/shortener/123") {
        }
    }
    response {
        body(
                originalUrl: "www.google.com",
                shortUrl: "123"
        )
        status 200
    }
}