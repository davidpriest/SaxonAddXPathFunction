package ca.davidpriest.saxon

import com.icl.saxon.expr.XPathException
import net.sf.saxon.expr.XPathContext
import net.sf.saxon.lib.{ExtensionFunctionCall, ExtensionFunctionDefinition}
import net.sf.saxon.om.{Sequence, StructuredQName}
import net.sf.saxon.value.{SequenceType, StringValue}

class MyFunctionDefinition extends ExtensionFunctionDefinition {
  override def getFunctionQName: StructuredQName =
    new StructuredQName(
      // default function name prefix, apparently not used
      "dcp",
      // function NS, used by saxon.config and XSLT namespace declaration
      "http://davidpriest.ca/saxon/myfunction",
      // function (local) name; called in XPath using {http://davidpriest.ca/saxon/myfunction}MyFunction(args)
      "MyFunction")

  override def getArgumentTypes: Array[SequenceType] =
    Array(SequenceType.SINGLE_STRING,
      SequenceType.SINGLE_STRING,
      SequenceType.SINGLE_STRING)

  override def getResultType(
                              suppliedArgumentTypes: Array[SequenceType]): SequenceType =
    SequenceType.SINGLE_STRING

  override def makeCallExpression: ExtensionFunctionCall = {
    new MyFunctionWrapper()
  }

  def MyFunction(first: String, second: String, third: String): String = {
    s"The first: $first, second: $second, and third: $third"
  }

  class MyFunctionWrapper extends ExtensionFunctionCall {
    private val MyFunctionObject: ca.davidpriest.saxon.MyFunctionDefinition =
      new ca.davidpriest.saxon.MyFunctionDefinition()

    @throws[net.sf.saxon.trans.XPathException]
    def call(context: XPathContext, args: Array[Sequence]): Sequence = {
      // set up parameters for function call
      var value: StringValue = new StringValue("")
      try {
        val first: String = args(0).toString
        val second: String = args(1).toString
        val third: String = args(2).toString
        // call our function
        value = StringValue.makeStringValue(
          MyFunctionObject.MyFunction(first, second, third))
      } catch {
        case e: XPathException => throw e
      }
      value
    }

  }

}
