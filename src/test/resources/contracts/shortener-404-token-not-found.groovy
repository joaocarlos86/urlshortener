package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "If the token is not found, then should return 404"
    request{
        method GET()
        url("/shortener/1233") {
        }
    }
    response {
        status NOT_FOUND()
    }
}