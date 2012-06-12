<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="utilerias.Bean3dChart"%>
<%@page import="de.laures.cewolf.DatasetProducer"%>

<%@taglib uri="/WEB-INF/cewolf.tld" prefix="cewolf"%>



<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />



<title>Originaci&oacute;n de Cliente</title>



    <link href="../css/main2.css" rel="stylesheet" type="text/css" />
<link href="../css/textos.css" rel="stylesheet" type="text/css" />
<link href="../css/formulario.css" rel="stylesheet" type="text/css" />
<link href="../css/formulario.css" rel="stylesheet" type="text/css" />
<link href="../css/formulario.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="../js/jquery-1.4.2.js"></script>
<script language="javascript" type="text/javascriptç" src="../js/funciones.js"></script>
<script language="javascript"  type="text/javascript"  src="../js/nuevogol.js"></script>
<script language="javascript" type="text/javascript" src="../js/jStorage.js"></script>

<script type="text/JavaScript">
<!--

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}


//-->
</script>

<script>
        $(document).ready(function (){

        cargarClientesGolCalculo();

        cargarJStorage();
        })
</script>
<style type="text/css">
.box{
	border-width:0;
	font-size: 13pt;
	font-weight:bold;
}
</style>



</head>
<body
	onload="MM_preloadImages('../images/acceso/activo-blanco_salir.png')">
<center>
<div id="contenedor">
<div id="encabezado">
<div id="borde_iz"></div>
<div id="logo"></div>
<div id="relleno">
<div id="capa_enc"><!-- InstanceBeginEditable name="region1" -->
<table width="321" border="0" valign="right" cellpadding="0"
	cellspacing="0" class="whiteSubtitle">
	<tr>
		<td width="226" colspan="9" align="center" class="blueSubtitle">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="9" align="center" class="blueSubtitle">Originaci&oacute;n
		de Cliente</td>
	</tr>
	<tr>
		<td colspan="9" align="center" class="whiteSubtitle">Calcular
		Originaci&oacute;n de Cliente</td>
	</tr>
	<tr>
		<td colspan="9" align="center" class="whiteSubtitle">&nbsp;</td>
	</tr>
</table>
<!-- InstanceEndEditable --></div>
</div>
<div id="borde_der"></div>
<div id="contenido"><!-- InstanceBeginEditable name="Region2" -->
<table width="822" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td align="left" valign="top">&nbsp;</td>
	</tr>
	<tr>
            <td width="207" rowspan="18" align="left" valign="top" style="">
			<form id="calcIndex" name="calcIndex" method="post" action="../GolServlet" onsubmit="return validate_form(this)">


                        <%

                        HttpSession sesion=request.getSession();


                        Object comportamiento=sesion.getAttribute("comportamiento")==null ? 0 :sesion.getAttribute("comportamiento"); ;
                          Object genero=sesion.getAttribute("genero")==null ? 0 :sesion.getAttribute("genero");
                      Object arraigo=sesion.getAttribute("arraigo")==null ? 0 :sesion.getAttribute("arraigo");
                      Object aspectos=sesion.getAttribute("aspectos")==null ? 0 :sesion.getAttribute("aspectos");

                                   DatasetProducer categoryData = new Bean3dChart(
                                Double.parseDouble(comportamiento.toString())
                                ,Double.parseDouble(genero.toString()),
                               Double.parseDouble(arraigo.toString()),
                                  Double.parseDouble(aspectos.toString()),
                                "Tipos de Documentos");

                            pageContext.setAttribute("categoryDataChar", categoryData);

                        %>


                        <select name="listaG" id="listaG" multiple="multiple" class="lista_200"
					onclick="calcularGol(this);"
                                        size="20" >
				</select>
			</form>


		</td>
	</tr>
	<tr>
		<td width="3" height="27" rowspan="18" align="left" valign="top"></td>
		<td height="27" colspan="6" rowspan="18" align="left" valign="top">
		<fieldset>
		<table width="443" height="277" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="15" valign="top">&nbsp;</td>
			</tr>
			<tr>
				<td height="16" valign="top" class="blackText2" align="center">
					<input id="nombre" name="nombre" readonly type="text" class="box" size="45" />
				</td>
			</tr>
			<tr>
				<td height="2" valign="top">&nbsp;</td>
			</tr>
			<tr>
				<td valign="top">
				<fieldset>
				<table width="460" height="221" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="293" rowspan="13" valign="top">
							<!-- GRAFICA -->


                                             	<cewolf:chart
                                                            id="horizontalBarChart3D"
                                                            title="Gráfica Gol"
                                                            type="horizontalBar3D"
                                                            xaxislabel="Datos"
                                                            yaxislabel="Rango">
								<cewolf:gradientpaint>
									<cewolf:point x="0" y="0" color="#FFFFFF" />
									<cewolf:point x="300" y="0" color="#DDDDFF" />
								</cewolf:gradientpaint>
								<cewolf:data>
									<cewolf:producer id="categoryDataChar" />
								</cewolf:data>
							</cewolf:chart>
							<cewolf:img chartid="horizontalBarChart3D" renderer="../cewolf" width="300" height="300"/>
							<!-- GRAFICA -->

						</td>
						<td width="149" class="blackText1" style="font-weight: bold"
							align="left">Composici&oacute;n</td>
					</tr>
					<tr>
						<td align="left" class="blackSmallText">Comportamiento de
						Pago:</td>
					</tr>
					<tr>
						<td align="left" class="blackSmallText">

                                                    <input 	name="desComportamiento" id="desComportamiento" type="text" class="textarea1" size="20" value=""
									readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td align="left" class="blackSmallText">G&eacute;nero y
						Clase:</td>
					</tr>
					<tr>
						<td align="left" class="blackSmallText">

							<input 	name="desGenero" id="desGenero" type="text" class="textarea1" size="20" value=""
									readonly="readonly"/>

						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td align="left" class="blackSmallText">Arraigo:</td>
					</tr>
					<tr>
						<td align="left" class="blackSmallText">
							<input 	name="desArraigo" id="desArraigo" type="text" class="textarea1" size="20" value=""
									readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td align="left" class="blackSmallText">Aspectos
						Diferenciadores:</td>
					</tr>
					<tr>
						<td align="left" class="blackSmallText">

							<input 	name="desAspectos" id="desAspectos" type="text" class="textarea1" size="20" value=""
									readonly="readonly"/></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
				</table>
				</fieldset>
				 </td>
			</tr>
		</table>
		</fieldset>
		</td>
		<td width="166" align="justify" valign="top">&nbsp;</td>
	</tr>
	<tr>
      <td align="left" valign="top">&nbsp;</td>
    </tr>
    <tr>
        <td align="left" valign="top"><input name="desGeneroClase1" id="desGeneroClase1" value="" type="text" class="textarea1" size="35" readonly="readonly"/></td>
    </tr>
    <tr>
      <td align="left" valign="top">&nbsp;</td>
    </tr>
    <tr>
      <td align="left" valign="top"><input name="desGeneroClase2" id="desGeneroClase2" value="" type="text" class="textarea1" size="35" readonly="readonly"/></td>
    </tr>
    <tr>
      <td align="left" valign="top">&nbsp;</td>
    </tr>
    <tr>
      <td align="justify" valign="top"><input name="desCompTex1" id="desCompTex1" value="" type="text" class="textarea1" size="35" readonly="readonly"/></td>
    </tr>
    <tr>
      <td align="justify" valign="top">&nbsp;</td>
    </tr>
    <tr>
      <td align="justify" valign="top"><input  name="desCompTex2" id="desCompTex2" value="" type="text" class="textarea1" size="35" readonly="readonly"/></td>
    </tr>
    <tr>
      <td align="justify" valign="top">&nbsp;</td>
    </tr>
    <tr>
      <td align="justify" valign="top"><input name="desArrTex1" id="desArrTex1" value="" type="text" class="textarea1" size="35" readonly="readonly"/></td>
    </tr>
    <tr>
      <td align="justify" valign="top">&nbsp;</td>
    </tr>
    <tr>
      <td align="justify" valign="top"><input name="desArrTex2" id="desArrTex2" value="" type="text" class="textarea1" size="35" readonly="readonly"/></td>
    </tr>
    <tr>
      <td align="justify" valign="top">&nbsp;</td>
    </tr>
    <tr>
      <td align="justify" valign="top"><input name="desAspTex1" id="desAspTex1" value="" type="text" class="textarea1" size="35" readonly="readonly"/></td>
    </tr>
    <tr>
      <td align="justify" valign="top">&nbsp;</td>
    </tr>
    <tr>
      <td align="justify" valign="top">&nbsp;</td>
    </tr>
    <tr>
      <td height="14" align="justify" valign="top">&nbsp;</td>
    </tr>
    <tr>
    <td height="14" colspan="8" valign="top" ><table width="271" border="0" cellspacing="0">
  <tr>
    <td width="269"><input id="resultado" name="resultado" readonly type="text" class="box" size="50" /></td>
    </tr>
</table>
      SCORE
      <input id="score" name="score" readonly="readonly" type="text" class="box" size="50" /></td>
    </tr>
	<tr>
		<td colspan="8" align="left" valign="top" class="blackSmallText">
		<div align="right">

			<a 	href="golindex.html"
                            onclick="$.jStorage.flush()"

				onmouseout="MM_swapImgRestore()"
				onmouseover="MM_swapImage('salir', '', '../images/acceso/activo-blanco_salir.png', 1);">
				<img 	src="../images/acceso/inactivo-blanco_salir.png" name="salir"
						width="30" height="30" border="0" id="salir" />
			</a><br/>
			<span class="blueSubtitle">Salir</span>
		</div>
		</td>
	</tr>
</table>
<!-- InstanceEndEditable --></div>
<div id="pie_pag"></div>
</div>
</div>
</center>
</body>
<!-- InstanceEnd -->
</html>
