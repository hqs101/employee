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
		<form action="${ctx }/admin/experience/save?id=${n.id }" id="detail-form" class="form-horizontal" method="post">
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
                 
				 <!-- 离职原因 -->
                 <label class="col-sm-2 control-label">离职原因</label>
                 <div class="col-sm-4">
                 	<input type="text" class="form-control" name="reason" value="${n.reason }">
                 </div>
            </div>
            <div class="form-group">     
                 
				 <!-- 单位 -->
                 <label class="col-sm-2 control-label">单位</label>
                 <div class="col-sm-4">
                 	<input type="text" class="form-control" name="company" value="${n.company }">
                 </div>
                 
				 <!-- 工作 -->
                 <label class="col-sm-2 control-label">工作</label>
                 <div class="col-sm-4">
                 	<input type="text" class="form-control" name="work" value="${n.work }">
                 </div>
            </div>
            <div class="form-group">     
                 
				 <!-- 职位 -->
                 <label class="col-sm-2 control-label">职位</label>
                 <div class="col-sm-4">
                 	<input type="text" class="form-control" name="position" value="${n.position }">
                 </div>
                 
				 <!-- 工作起始时间 -->
                 <label class="col-sm-2 control-label">工作起始时间</label>
                 <div class="col-sm-4">
                 	<input type="text" class="form-control" name="start" value="<fmt:formatDate value='${n.start }' pattern=''/>" onclick="laydate({format: ''})">
                 </div>
            </div>
            <div class="form-group">     
                 
				 <!-- 工作结束时间 -->
                 <label class="col-sm-2 control-label">工作结束时间</label>
                 <div class="col-sm-4">
                 	<input type="text" class="form-control" name="end" value="<fmt:formatDate value='${n.end }' pattern=''/>" onclick="laydate({format: ''})">
                 </div>
                 
             </div>	
        	
		</form>
</div>

<%@include file="/common/foot.jsp" %>

</body>
</html>
