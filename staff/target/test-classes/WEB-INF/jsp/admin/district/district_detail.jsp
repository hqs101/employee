<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@include file="/common/head.jsp" %>

<c:set var="module" value="district"/>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
		<form action="${ctx }/admin/district/save?id=${n.id }" id="detail-form" class="form-horizontal" method="post">
			<div class="form-group">
                 <label class="col-sm-2 control-label">地区名称</label>
                 <div class="col-sm-10">
                 	<input type="text" class="form-control" name="name" value="${n.name }">
                 </div>
             </div>	
			<div class="form-group">
                 <label class="col-sm-2 control-label">城市</label>
                 <div class="col-sm-10">
                 	<input type="text" class="form-control" name="city.id" value="${n.city.id }">
                 </div>
             </div>	
			<div class="form-group">
                 <label class="col-sm-2 control-label">字母</label>
                 <div class="col-sm-10">
                 	<input type="text" class="form-control" name="letter" value="${n.letter }">
                 </div>
             </div>	
			<div class="form-group">
                 <label class="col-sm-2 control-label">全拼音</label>
                 <div class="col-sm-10">
                 	<input type="text" class="form-control" name="fullLetter" value="${n.fullLetter }">
                 </div>
             </div>	
        	
		</form>
</div>

<%@include file="/common/foot.jsp" %>

</body>
</html>
