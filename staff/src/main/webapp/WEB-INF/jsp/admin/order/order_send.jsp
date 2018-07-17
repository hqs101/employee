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
		<form action="${ctx }/admin/order/updateStatusToSend" id="order-send-form" class="form-horizontal" method="post">
			<input type="hidden" name="orderId" value="${n.id }"/>
			
             <div class="form-group">
                 <label class="col-sm-2 control-label">运单编号</label>
                 <div class="col-sm-10">
                 	<input type="text" class="form-control" id="express" name="express" placeholder="请输入运单编号">
                 </div>
             </div>	
		</form>
</div>

<%@include file="/common/foot.jsp" %>

</body>
</html>
