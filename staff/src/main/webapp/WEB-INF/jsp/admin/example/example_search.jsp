<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@include file="/common/head.jsp" %>

<c:set var="module" value="example"/>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                    	<form role="form" class="form-inline" id="query-form" method="post" action="${ctx }/admin/example/list" >
                    		<!-- 查询开始 -->
                            <div class="form-group">
                                <label for="query-username" class="sr-only">用户名</label>
                                	<input type="text" placeholder="用户名" id="query-username" name="username" value="${query.username }" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="query-createDate" class="sr-only">创建日期</label>
                                	<input type="text" placeholder="创建日期" id="query-createDate" name="createDate" value='<fmt:formatDate value="${query.createDate }" pattern="yyyy-MM-dd"/>' class="form-control" onclick="laydate({format: 'YYYY-MM-DD'})">
                            </div>
                            <div class="form-group">
                                <label for="query-myname" class="sr-only">姓名</label>
                                	<input type="text" placeholder="姓名" id="query-myname" name="myname" value="${query.myname }" class="form-control">
                            </div>
                            <div class="form-group">
                            	<button class="btn btn-primary" type="submit"><i class="fa fa-search"></i>&nbsp;查询</button>
                            </div>
                            <!-- 查询结束 -->
                        </form>
                    
						<div id="operation">
							<p>
								
		                        <sec:authorize access="hasRole('user.add')">
		                        <button type="button" class="btn btn-primary" onclick="">拥有user.add权限才能看到我</button>
		                        </sec:authorize>
		                        <button type="button" class="btn btn-primary" onclick="add()"><i class="fa fa-plus"></i> 新增</button>
		                        <button type="button" class="btn btn-info " onclick="edit()"><i class="fa fa-paste"></i> 详情</button>
		                        <button type="button" class="btn btn-danger" onclick="del();"><i class="fa fa-remove"></i> 删除</button>
		                        <button type="button" class="btn btn-outline btn-primary" onclick="exp()">导出</button>
		                        <button type="button" class="btn btn-outline btn-primary" onclick="exp()">富文本</button>
		                        <button type="button" class="btn btn-info " onclick="reOpen()"><i class="fa fa-refresh"></i> 刷新</button>
		                    </p>
						</div>
						<table id="table" data-toggle="table" data-striped="true" data-height="500" data-click-to-select="true" data-mobile-responsive="true">
                                    <thead>
                                        <tr>
                                        	<!-- data-field不能有重复的 -->
                                            <th data-field="state" data-checkbox="true"></th>
                                            <th data-field="id" data-visible="false">id</th>
                                            <th data-field="username">用户名</th>
                                            <th data-field="createDate">创建日期</th>
                                            <th data-field="myname">姓名</th>
                                            <th data-field="operation">操作</th>
                                        </tr>
                                    </thead>
							<tbody>
								<c:forEach items="${list }" var="n" varStatus="idx">
								<tr data-index="${idx.index }">
									<td class="bs-checkbox">
										<input data-index="0" name="btSelectItem" type="checkbox">
									</td>
									<td style="">${n.id }</td>
									<td style="">${n.username }</td>
									<td style="">
										<fmt:formatDate value="${n.createDate }" pattern="yyyy-MM-dd"/>
									</td>
									<td style="">${n.myname }</td>
									<td>
										<a href="">详情</a>
									</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
						
						<%@include file="/common/page.jsp" %>
						
					</div>
                </div>
            </div>
        </div>
    </div>

<%@include file="/common/foot.jsp" %>
<script>
/*
 * k.layer.add有三个参数，第一个参数是url，第二个参数是json格式的参数，比如{id:1, name:'2'}，
 第三个参数是成功的回调参数，一般都不需要传第二个第三个参数。如果需要更多灵活的方式，就自己写layer.open
 */
function add() {
	k.layer.add("${ctx}/admin/example/detail");	
}

/*
 * 如果取不到id就这样去取选中的记录
 var selections = $('#table').bootstrapTable('getAllSelections');
	if (selections.length != 1) {
		error2('请选择一条记录');
		return;
	}
	var id = selections[0].id;
	
	k.layer.edit有三个参数，第一个参数是url，第二个参数是json格式的参数，比如{id:1, name:'2'}，
	 第三个参数是成功的回调参数，一般都不需要传第二个第三个参数。如果需要更多灵活的方式，就自己写layer.open
	 如果参数简单，可以就在url里加上参数
 */
function edit(id) {
	k.layer.edit("${ctx}/admin/example/detail", {id: id});	
}

var page = "${page.page}";
function reload() {
	page_submit(page);
}
function del() {
	var selections = $('#table').bootstrapTable('getAllSelections');
	if (selections.length < 1) {
		error('请选择要删除的记录');
		return;
	}
	var ids = new Array();
	for (var i = 0; i < selections.length; i++) {
		ids.push(selections[i].id);
	}
	$.ajax({
		url: "${ctx}/admin/example/delete",
		data: {
			ids: ids.join(',')
		},
		success: function(data) {
			if (isSuccess(data)) {
				reload();
			}
		}
	});
}

function exp() {
	alert("1");
	var url = "${ctx }/admin/example/expExcel";
	$.ajax({
		url: url
	});
}

paging('pagination', ${page.totalPages}, ${page.page});

</script>
</body>
</html>