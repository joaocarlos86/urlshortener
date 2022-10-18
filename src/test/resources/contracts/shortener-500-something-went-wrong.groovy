package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "If the token is not found, then should return 404"
    request{
        method GET()
        url("/shortener/1") {
        }
    }
    response {
        status INTERNAL_SERVER_ERROR()
    }
}