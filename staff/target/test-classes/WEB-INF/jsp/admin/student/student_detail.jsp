<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@include file="/common/head.jsp" %>

<c:set var="module" value="student"/>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
		<form action="${ctx }/admin/student/save?id=${n.id }" id="detail-form" class="form-horizontal" method="post">
			<div class="form-group">
				 <!-- 姓名 -->
                 <label class="col-sm-2 control-label">姓名</label>
                 <div class="col-sm-4">
                 	<input type="text" class="form-control" name="name" value="${n.name }">
                 </div>
                 
				 <!-- 年龄 -->
                 <label class="col-sm-2 control-label">年龄</label>
                 <div class="col-sm-4">
                 	<input type="text" class="form-control" name="age" value="${n.age }">
                 </div>
            </div>
            <div class="form-group">     
                 
             </div>	
        	
		</form>
</div>

<%@include file="/common/foot.jsp" %>

</body>
</html>
