
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import scala.collection.JavaConverters._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import play.data._
import play.core.j.PlayFormsMagicForJava._

object projectDetails extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[models.Project,models.users.User,play.api.Environment,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(p: models.Project,user: models.users.User,env: play.api.Environment):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.71*/("""

"""),_display_(/*3.2*/main("Full Image", user)/*3.26*/ {_display_(Seq[Any](format.raw/*3.28*/("""

  """),format.raw/*5.3*/("""<div class="col-sm-10">
    <table class="table table-bordered table-hover table-condensed">
      """),_display_(/*7.8*/if(flash.containsKey("success"))/*7.40*/ {_display_(Seq[Any](format.raw/*7.42*/("""
        """),format.raw/*8.9*/("""<div class="alert alert-success">
          """),_display_(/*9.12*/flash/*9.17*/.get("success")),format.raw/*9.32*/("""
        """),format.raw/*10.9*/("""</div>
      """)))}),format.raw/*11.8*/("""
      """),format.raw/*12.7*/("""<thead>
        <tr>
          <th>Image</th> 
          <th>ID</th> 
        </tr>
      </thead>

      <tbody>

          <tr>
              """),_display_(/*22.16*/if(env.resource("public/images/projectImages/" + p.getId + ".jpg").isDefined)/*22.93*/ {_display_(Seq[Any](format.raw/*22.95*/("""
                """),format.raw/*23.17*/("""<td><img src="/assets/images/projectImages/"""),_display_(/*23.61*/(p.getId + ".jpg")),format.raw/*23.79*/(""""/></td>
            """)))}/*24.15*/else/*24.20*/{_display_(Seq[Any](format.raw/*24.21*/("""
                """),format.raw/*25.17*/("""<td><img src="/assets/images/projectImages/noImage.jpg"/></td>
            """)))}),format.raw/*26.14*/("""
            """),format.raw/*27.13*/("""<td class="numeric">"""),_display_(/*27.34*/p/*27.35*/.getId),format.raw/*27.41*/("""</td>
            
          </tr>
        
      </tbody>

    </table>

    
  </div>
</div>
""")))}),format.raw/*38.2*/("""
"""))
      }
    }
  }

  def render(p:models.Project,user:models.users.User,env:play.api.Environment): play.twirl.api.HtmlFormat.Appendable = apply(p,user,env)

  def f:((models.Project,models.users.User,play.api.Environment) => play.twirl.api.HtmlFormat.Appendable) = (p,user,env) => apply(p,user,env)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Tue Mar 13 11:38:03 GMT 2018
                  SOURCE: /home/wdd/Documents/Sdev/SDEVCAPlayFramework/lab2/app/views/projectDetails.scala.html
                  HASH: 361b28726e1535dfc6aec245f16f1b662e8946ad
                  MATRIX: 1004->1|1168->70|1196->73|1228->97|1267->99|1297->103|1422->203|1462->235|1501->237|1536->246|1607->291|1620->296|1655->311|1691->320|1735->334|1769->341|1941->486|2027->563|2067->565|2112->582|2183->626|2222->644|2263->667|2276->672|2315->673|2360->690|2467->766|2508->779|2556->800|2566->801|2593->807|2719->903
                  LINES: 28->1|33->1|35->3|35->3|35->3|37->5|39->7|39->7|39->7|40->8|41->9|41->9|41->9|42->10|43->11|44->12|54->22|54->22|54->22|55->23|55->23|55->23|56->24|56->24|56->24|57->25|58->26|59->27|59->27|59->27|59->27|70->38
                  -- GENERATED --
              */
          