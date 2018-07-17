<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@include file="/common/bootstrap-jquery-vue-head.jsp" %>


<body>
	<div class="container">
		<div class="row">
			<div class="col-md-10">
				<select name="" id="file" class="form-control">
					<c:forEach items="${files }" var="f">
						<option value="${f.name }">${f.name }</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-md-2">
				<button class="btn btn-primary" onclick="window.open('${ctx}/dev/downloadLog?file=' + $('#file').val())">下载</button>
			</div>
		</div>
	</div>
</body>
</html>