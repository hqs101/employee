<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@include file="/common/head.jsp"%>

<div class="row" style="margin-top: 100px;">
	<div class="col-xs-4 col-xs-offset-4" style="text-align: center;">
		<img src="${ctx }/img/ico/fail.png" alt="" style="width: 100px;"/>
	</div>
	<div class="col-xs-4 col-xs-offset-4" style="text-align: center; font-size: 24px; margin-top: 20px;">
		${msg }<br/>
		<div style="font-size: 18px;">
			<a href="javascript:void(0);" onclick="history.go(-1)">返回</a>
		</div>
		<div style="font-size: 13px; margin-top: 20px;">
			<a href="javascript:void(0);" id="detail" onclick="$('#detail-div').show()">详情</a>
		</div>
	</div>
	<div class="col-xs-10 col-xs-offset-1" style="margin-top: 10px; display: none;" id="detail-div">
		<c:if test="${not empty stacks }">
		<div style="font-size: 15px;">
			<c:forEach items="${stacks }" var="s">
				${s }
			</c:forEach>
		</div>
		</c:if>
	</div>
</div>

<%@include file="/common/foot.jsp"%>

<script>
	
</script>
</body>
</html>
