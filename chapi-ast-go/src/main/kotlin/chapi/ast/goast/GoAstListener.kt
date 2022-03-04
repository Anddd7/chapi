package chapi.ast.goast

import chapi.ast.antlr.GoParser
import chapi.ast.antlr.GoParserBaseListener
import chapi.domain.core.CodeProperty

open class GoAstListener : GoParserBaseListener() {
    fun buildParameters(parametersCtx: GoParser.ParametersContext?): Array<CodeProperty> {
        var parameters: Array<CodeProperty> = arrayOf()
        for (paramCtx in parametersCtx!!.parameterDecl()) {
            val parameter = CodeProperty(
                TypeValue = paramCtx.identifierList().text,
                TypeType = paramCtx.type_().text
            )

            parameters += parameter
        }
        return parameters
    }

    fun getStructNameFromReceiver(parameters: GoParser.ParametersContext?): String? {
        val parameterDecls = parameters!!.parameterDecl()
        for (paramCtx in parameterDecls) {
            var typeType = paramCtx.type_().text
            if (typeType.startsWith("*")) {
                typeType = typeType.removePrefix("*")
            }
            return typeType
        }

        return ""
    }

    protected fun buildReturnTypeFromSignature(signatureContext: GoParser.SignatureContext?): Array<CodeProperty> {
        val result = signatureContext!!.result()
        var returns: Array<CodeProperty> = arrayOf()
        if (result != null) {
            val parameters = result.parameters()
            if (parameters != null) {
                for (parameterDeclContext in parameters.parameterDecl()) {
                    val typeType = parameterDeclContext.text
                    val returnType =
                        CodeProperty(TypeType = typeType, TypeValue = "")
                    returns += returnType
                }
            }

            if (result.type_() != null) {
                val typeType = result.type_().text
                val returnType = CodeProperty(TypeType = typeType, TypeValue = "")
                returns += returnType
            }
        }

        return returns;
    }

}
