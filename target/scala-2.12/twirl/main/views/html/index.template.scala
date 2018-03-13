
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

object index extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template4[List[models.Project],List[models.Department],models.users.User,play.api.Environment,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(projects: List[models.Project], departments: List[models.Department], user: models.users.User,env: play.api.Environment):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.123*/("""

"""),_display_(/*3.2*/main("Projects",user)/*3.23*/ {_display_(Seq[Any](format.raw/*3.25*/("""

"""),format.raw/*5.1*/("""<p class="lead">Project Summary</p>
<br>
<div class="row">
  <div class="col-sm-2">
    <h4>Departments</h4>
    <div class="list-group">
      <a href=""""),_display_(/*11.17*/routes/*11.23*/.HomeController.index(0)),format.raw/*11.47*/("""" class="list-group-item">All Departments</a>
      """),_display_(/*12.8*/for(c <- departments) yield /*12.29*/ {_display_(Seq[Any](format.raw/*12.31*/("""
        """),format.raw/*13.9*/("""<a href=""""),_display_(/*13.19*/routes/*13.25*/.HomeController.index(c.getId)),format.raw/*13.55*/("""" class="list-group-item">"""),_display_(/*13.82*/c/*13.83*/.getName),format.raw/*13.91*/("""
          """),format.raw/*14.11*/("""<span class="badge">"""),_display_(/*14.32*/c/*14.33*/.getProjects.size()),format.raw/*14.52*/("""</span>
        </a>
    """)))}),format.raw/*16.6*/("""
    """),format.raw/*17.5*/("""</div>
  </div>
  <div class="col-sm-10">
    <table class="table table-bordered table-hover table-condensed">
      """),_display_(/*21.8*/if(flash.containsKey("success"))/*21.40*/ {_display_(Seq[Any](format.raw/*21.42*/("""
        """),format.raw/*22.9*/("""<div class="alert alert-success">
          """),_display_(/*23.12*/flash/*23.17*/.get("success")),format.raw/*23.32*/("""
        """),format.raw/*24.9*/("""</div>
      """)))}),format.raw/*25.8*/("""
      """),format.raw/*26.7*/("""<thead>
        <tr>
          <th>Picture</th>
          <th>ID</th>
          <th>Name</th>
          <th>Department</th>
          <th>Project Involved In</th>
          <th>Edit</th>
          <th>Delete</th>
          <th colspan="2"></th>      
        </tr>
      </thead>

      <tbody>
        """),_display_(/*40.10*/for(p<-projects) yield /*40.26*/ {_display_(Seq[Any](format.raw/*40.28*/("""
          """),format.raw/*41.11*/("""<tr>
              """),_display_(/*42.16*/if(env.resource("public/images/projectImages/thumbnails/" + p.getId + ".jpg").isDefined)/*42.104*/ {_display_(Seq[Any](format.raw/*42.106*/("""
                """),format.raw/*43.17*/("""<td><img src="/assets/images/projectImages/thumbnails/"""),_display_(/*43.72*/(p.getId + ".jpg")),format.raw/*43.90*/(""""/></td>
            """)))}/*44.15*/else/*44.20*/{_display_(Seq[Any](format.raw/*44.21*/("""
                """),format.raw/*45.17*/("""<td><img src="/assets/images/projectImages/thumbnails/noImage.jpg"/></td>
            """)))}),format.raw/*46.14*/("""
            """),format.raw/*47.13*/("""<td class="numeric">"""),_display_(/*47.34*/p/*47.35*/.getId),format.raw/*47.41*/("""</td>
            <td><a href=""""),_display_(/*48.27*/routes/*48.33*/.HomeController.projectDetails(p.getId)),format.raw/*48.72*/("""">
                """),_display_(/*49.18*/p/*49.19*/.getName),format.raw/*49.27*/("""</td>
               </a>
            <td>"""),_display_(/*51.18*/p/*51.19*/.getDepartment.getName),format.raw/*51.41*/("""</td>        
            <td>"""),_display_(/*52.18*/p/*52.19*/.getDescription),format.raw/*52.34*/("""</td>
            
            <td>
              <a href=""""),_display_(/*55.25*/routes/*55.31*/.HomeController.updateProject(p.getId)),format.raw/*55.69*/("""" class="button xs btn-danger">
                <span class="glyphicon glyphicon-pencil"></span>
              </a>
            </td>        <td>
              <a href=""""),_display_(/*59.25*/routes/*59.31*/.HomeController.deleteProject(p.getId)),format.raw/*59.69*/("""" class="button xs btn-danger" onclick="return confirmDel();">
                <span class="glyphicon glyphicon-trash"></span>
              </a>
            </td>
          </tr>
        """)))}),format.raw/*64.10*/("""
      """),format.raw/*65.7*/("""</tbody>

    </table>

    <p>
      <a href=""""),_display_(/*70.17*/routes/*70.23*/.HomeController.addProject()),format.raw/*70.51*/("""">
        <button class="btn btn-primary">Add a project</button>
      </a>
    </p>
  </div>
</div>
""")))}),format.raw/*76.2*/("""
"""))
      }
    }
  }

  def render(projects:List[models.Project],departments:List[models.Department],user:models.users.User,env:play.api.Environment): play.twirl.api.HtmlFormat.Appendable = apply(projects,departments,user,env)

  def f:((List[models.Project],List[models.Department],models.users.User,play.api.Environment) => play.twirl.api.HtmlFormat.Appendable) = (projects,departments,user,env) => apply(projects,departments,user,env)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Tue Mar 13 11:38:03 GMT 2018
                  SOURCE: /home/wdd/Documents/Sdev/SDEVCAPlayFramework/lab2/app/views/index.scala.html
                  HASH: 3ee118fbead06f848bfb13a455364f9c7f10990c
                  MATRIX: 1025->1|1242->122|1270->125|1299->146|1338->148|1366->150|1547->304|1562->310|1607->334|1686->387|1723->408|1763->410|1799->419|1836->429|1851->435|1902->465|1956->492|1966->493|1995->501|2034->512|2082->533|2092->534|2132->553|2188->579|2220->584|2364->702|2405->734|2445->736|2481->745|2553->790|2567->795|2603->810|2639->819|2683->833|2717->840|3048->1144|3080->1160|3120->1162|3159->1173|3206->1193|3304->1281|3345->1283|3390->1300|3472->1355|3511->1373|3552->1396|3565->1401|3604->1402|3649->1419|3767->1506|3808->1519|3856->1540|3866->1541|3893->1547|3952->1579|3967->1585|4027->1624|4074->1644|4084->1645|4113->1653|4183->1696|4193->1697|4236->1719|4294->1750|4304->1751|4340->1766|4427->1826|4442->1832|4501->1870|4698->2040|4713->2046|4772->2084|4992->2273|5026->2280|5101->2328|5116->2334|5165->2362|5298->2465
                  LINES: 28->1|33->1|35->3|35->3|35->3|37->5|43->11|43->11|43->11|44->12|44->12|44->12|45->13|45->13|45->13|45->13|45->13|45->13|45->13|46->14|46->14|46->14|46->14|48->16|49->17|53->21|53->21|53->21|54->22|55->23|55->23|55->23|56->24|57->25|58->26|72->40|72->40|72->40|73->41|74->42|74->42|74->42|75->43|75->43|75->43|76->44|76->44|76->44|77->45|78->46|79->47|79->47|79->47|79->47|80->48|80->48|80->48|81->49|81->49|81->49|83->51|83->51|83->51|84->52|84->52|84->52|87->55|87->55|87->55|91->59|91->59|91->59|96->64|97->65|102->70|102->70|102->70|108->76
                  -- GENERATED --
              */
          