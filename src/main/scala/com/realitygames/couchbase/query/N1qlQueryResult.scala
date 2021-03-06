package com.realitygames.couchbase.query

import play.api.libs.json.{JsObject, JsString}

sealed abstract class N1qlQueryResult[+T]

object N1qlQueryResult {

  case class FailureN1qlQueryResult(errors: List[JsObject]) extends N1qlQueryResult[Nothing]

  object FailureN1qlQueryResult{
    def apply(errorMessage: String): FailureN1qlQueryResult = FailureN1qlQueryResult(JsObject(Map(
      "msg" -> JsString(errorMessage)
    ))::Nil)
  }

  case class SuccessN1qlQueryResult[T](
    values: Seq[T],
    totalResults: Int,
    parseFailedDocuments: Seq[ParseFailedN1ql]
  ) extends N1qlQueryResult[T]
}
