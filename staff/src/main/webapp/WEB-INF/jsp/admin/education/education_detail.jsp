<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@include file="/common/head.jsp" %>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
		<form action="${ctx }/admin/education/save?id=${n.id }" id="detail-form" class="form-horizontal" method="post">
			<div class="form-group">
				 <!-- 员工ID -->
                 <label class="col-sm-2 control-label">员工ID</label>
                 <div class="col-sm-4">
                 	<input type="hidden" id="employee-id" name="employee.id" value="${n.employee.id }" class="form-control">
                 	<input type="text"  placeholder="请选择员工ID" class="form-control" id="employee-name" value="${n.employee.id }"
                 		onclick="bring({
						                 	url:'${ctx}/admin/employee/search',
						                 	name: 'id',
						                 	idInput: 'employee-id',
						                 	nameInput: 'employee-name'
						                 	})" />

                 </div>
                 
				 <!-- 学习起始时间 -->
                 <label class="col-sm-2 control-label">学习起始时间</label>
                 <div class="col-sm-4">
                 	<input type="text" class="form-control" name="start" value="<fmt:formatDate value='${n.start }' pattern='yyyy-MM-dd'/>" onclick="laydate({format: ''})">
                 </div>
            </div>
            <div class="form-group">
                 
				 <!-- 学习结束时间 -->
                 <label class="col-sm-2 control-label">学习结束时间</label>
                 <div class="col-sm-4">
                 	<input type="text" class="form-control" name="end" value="<fmt:formatDate value='${n.end }' pattern='yyyy-MM-dd'/>" onclick="laydate({format: ''})">
                 </div>
                 
				 <!-- 专业 -->
                 <label class="col-sm-2 control-label">专业</label>
                 <div class="col-sm-4">
                 	<input type="text" class="form-control" name="major" value="${n.major }">
                 </div>
            </div>
            <div class="form-group">     
                 
				 <!-- 学历 -->
                 <label class="col-sm-2 control-label">学历</label>
                 <div class="col-sm-4">
                 	<input type="text" class="form-control" name="record" value="${n.record }">
                 </div>
                 
				 <!-- 证书 -->
                 <label class="col-sm-2 control-label">证书</label>
                 <div class="col-sm-4">
                 	<input type="text" class="form-control" name="certificate" value="${n.certificate }">
                 </div>
            </div>
            <div class="form-group">

             </div>
        	
		</form>
</div>

<%@include file="/common/foot.jsp" %>

</body>
</html>
