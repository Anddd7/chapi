package chapi.ast.kotlinast

import chapi.ast.antlr.KotlinParser
import chapi.ast.antlr.KotlinParserBaseListener

class KotlinFullIdentListener() : KotlinAstListener() {
    override fun enterFunctionDeclaration(ctx: KotlinParser.FunctionDeclarationContext?) {
        super.enterFunctionDeclaration(ctx)
    }

    fun getNodeInfo() {

    }
}
