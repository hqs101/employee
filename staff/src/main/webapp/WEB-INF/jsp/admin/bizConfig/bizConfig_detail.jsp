<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@include file="/common/head.jsp" %>
<c:set var="module" value="bizConfig"/>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
		<form action="${ctx }/admin/bizConfig/save?id=${n.id }" id="detail-form" class="form-horizontal" method="post">
			<div class="form-group">
                 <label class="col-sm-2 control-label">参数名</label>
                 <div class="col-sm-10">
                 	<input type="text" class="form-control" disabled="disabled" name="name" value="${n.name }">
                 </div>
             </div>	
			<div class="form-group">
                 <label class="col-sm-2 control-label">参数值</label>
                 <div class="col-sm-10">
                 	<%-- <input type="text" class="form-control" name="value" value="${n.value }"> --%>
                 	<c:if test="${n.isRichText }">
                 		<script id="editor" type="text/plain"  style="width:649px;height:500px;">${n.value }</script>
                 		<input type="hidden" id="value" name="value" />
                 	</c:if>
                 	<c:if test="${!n.isRichText }">
                 		<textarea rows="10" cols="90" name="value">${n.value }</textarea>
                 	</c:if>
                 </div>
             </div>	
			<%-- <div class="form-group">
                 <label class="col-sm-2 control-label">备注</label>
                 <div class="col-sm-10">
                 	<input type="text" class="form-control" name="remark" value="${n.remark }">
                 </div>
             </div>	
			<div class="form-group">
                 <label class="col-sm-2 control-label">创建时间</label>
                 <div class="col-sm-10">
                 	<input type="text" class="form-control" name="createdTime" value="<fmt:formatDate value='${n.createdTime }' pattern='yyyy-MM-dd HH:mm:ss'/>" onclick="laydate({format: 'YYYY-MM-DD hh:mm:ss'})">
                 </div>
             </div>	
			<div class="form-group">
                 <label class="col-sm-2 control-label">是否是富文本</label>
                 <div class="col-sm-10">
                 	<input type="text" class="form-control" name="isRichText" value="${n.isRichText }">
                 </div>
             </div>	 --%>
        	
		</form>
</div>

<%@include file="/common/foot.jsp" %>
<script type="text/javascript">

    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');
	$(function(){
	    ue.addListener( 'selectionchange', function( editor ) {
	        var content = ue.getContent();
	        $('#value').val(content);
	    });
	});

</script>
</body>
</html>
