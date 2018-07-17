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
		<form action="${ctx }/admin/family/save?id=${n.id }" id="detail-form" class="form-horizontal" method="post">
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
                 
				 <!-- 姓名 -->
                 <label class="col-sm-2 control-label">姓名</label>
                 <div class="col-sm-4">
                 	<input type="text" class="form-control" name="name" value="${n.name }">
                 </div>
            </div>
            <div class="form-group">     
                 
				 <!-- 关系 -->
                 <label class="col-sm-2 control-label">关系</label>
                 <div class="col-sm-4">
                 	<input type="text" class="form-control" name="relation" value="${n.relation }">
                 </div>
                 
				 <!-- 联系方式 -->
                 <label class="col-sm-2 control-label">联系方式</label>
                 <div class="col-sm-4">
                 	<input type="text" class="form-control" name="contact" value="${n.contact }">
                 </div>
            </div>
            <div class="form-group">     
                 
				 <!-- 年龄 -->
                 <label class="col-sm-2 control-label">年龄</label>
                 <div class="col-sm-4">
                 	<input type="text" class="form-control" name="age" value="${n.age }">
                 </div>
                 
				 <!-- 工作单位 -->
                 <label class="col-sm-2 control-label">工作单位</label>
                 <div class="col-sm-4">
                 	<input type="text" class="form-control" name="company" value="${n.company }">
                 </div>
            </div>
            <div class="form-group">     
                 
				 <!-- 职位 -->
                 <label class="col-sm-2 control-label">职位</label>
                 <div class="col-sm-4">
                 	<input type="text" class="form-control" name="position" value="${n.position }">
                 </div>
                 
             </div>	
        	
		</form>
</div>

<%@include file="/common/foot.jsp" %>

</body>
</html>
