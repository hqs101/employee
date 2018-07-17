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
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                    	<form role="form" class="form-inline" id="query-form" method="post" action="${ctx }/admin/employee/list" >
                    		<!-- 查询开始 -->
                            <div class="form-group">
                                <label for="query-user" class="sr-only2">用户ID</label>
                                	<input type="hidden" id="query-user-id" name="user.id" value="${query.user.id }" class="form-control">
				                 	<input type="text" class="form-control" id="user-name" name="user.id" value="${query.user.id }" 
				                 		onclick="bring({
										                 	url:'${ctx}/admin/user/search',
										                 	name: 'id',
										                 	idInput: 'user-id',
										                 	nameInput: 'user-name'
										                 	})" />	
                            </div>
                            <div class="form-group">
                                <label for="query-name" class="sr-only2">姓名</label>
                                	<input type="text" id="query-name" name="name" value="${query.name }" class="form-control input-sm">
                                
                            </div>
                            <div class="form-group">
                                <label for="query-phone" class="sr-only2">联系电话</label>
                                	<input type="text" id="query-phone" name="phone" value="${query.phone }" class="form-control input-sm">
                                
                            </div>
                            <div class="form-group">
                                <label for="query-status" class="sr-only2">员工状态/1.录用/2.停职</label>
                                	<input type="text" id="query-status" name="status" value="${query.status }" class="form-control input-sm">
                                
                            </div>
                            <div class="form-group">
                            	<button class="btn btn-primary btn-sm" type="submit"><i class="fa fa-search"></i>&nbsp;查询</button>
                            </div>
                            <!-- 查询结束 -->
                        </form>
                    
						<div>
							<p>
		                        <button type="button" class="btn btn-sm btn-outline btn-primary" onclick="add()"><i class="fa fa-plus"></i> 新增</button>
		                        <button type="button" class="btn btn-sm btn-outline btn-info " onclick="edit()"><i class="fa fa-paste"></i> 详情</button>
		                        <button type="button" class="btn btn-sm btn-outline btn-danger" onclick="del();">删除</button>
		                        <button type="button" class="btn btn-sm btn-outline btn-info " onclick="reOpen()"><i class="fa fa-refresh"></i> 刷新</button>
		                    </p>
						</div>
						<table id="table" data-toggle="table" data-striped="true" data-click-to-select="true" data-mobile-responsive="true">
                                    <thead>
                                        <tr>
                                            <th data-field="_state" data-checkbox="true"></th>
                                            <th data-field="id" data-visible="false">ID</th>
                                            <th data-field="user">用户ID</th>
                                            <th data-field="position">岗位</th>
                                            <th data-field="name">姓名</th>
                                            <th data-field="birth">出生日期</th>
                                            <th data-field="sex">性别</th>
                                            <th data-field="height">身高</th>
                                            <th data-field="weight">体重</th>
                                            <th data-field="place">籍贯</th>
                                            <th data-field="nation">民族</th>
                                            <th data-field="phone">联系电话</th>
                                            <th data-field="iDCard">身份证号</th>
                                            <th data-field="school">毕业学校</th>
                                            <th data-field="major">专业</th>
                                            <th data-field="education">学历</th>
                                            <th data-field="location">户口所在地</th>
                                            <th data-field="address">居住地址</th>
                                            <th data-field="wechat">微信号</th>
                                            <th data-field="email">邮箱</th>
                                            <th data-field="marry">婚姻状况</th>
                                            <th data-field="inTime">入职时间</th>
                                            <th data-field="status">员工状态/1.录用/2.停职</th>
                                        </tr>
                                    </thead>
							<tbody>
								<c:forEach items="${list }" var="n" varStatus="idx">
								<tr data-index="${idx.index }" data-id="${n.id }">
									<td class="bs-checkbox">
										<input data-index="0" name="btSelectItem" type="checkbox">
									</td>
									<td style="">${n.id }</td>
									<td style="">${n.user.id}</td>
									<td style="">${n.position }</td>
									<td style="">${n.name }</td>
									<td style="">
										<fmt:formatDate value="${n.birth }" pattern="yyyy.MM.dd"/>
									</td>
									<td style="">${n.sex == 1 ? "男" : "女" }</td>
									<td style="">${n.height }</td>
									<td style="">${n.weight }</td>
									<td style="">${n.place }</td>
									<td style="">${n.nation }</td>
									<td style="">${n.phone }</td>
									<td style="">${n.iDCard }</td>
									<td style="">${n.school }</td>
									<td style="">${n.major }</td>
									<td style="">${n.education == 1 ? "本科" : (n.education == 2 ? "高中" : (n.education == 3 ? "硕士" : "其他") ) }</td>
									<td style="">${n.location }</td>
									<td style="">${n.address }</td>
									<td style="">${n.wechat }</td>
									<td style="">${n.email }</td>
									<td style="">${n.marry == 1 ? "未婚" : (n.marry == 2 ? "已婚" : "离异") }</td>
									<td style="">
										<fmt:formatDate value="${n.inTime }" pattern="yyyy.MM.dd"/>
									</td>
									<td>${ n.status == 1 ? "录用" : "停职"}</td>
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
function add() {
    k.layer.add("${ctx}/admin/employee/add");
}

function edit() {
    var selections = $('#table').bootstrapTable('getAllSelections');
	if (selections.length != 1) {
		error2(k.msg.requireOne);
		return;
	}
	var id = selections[0].id;
	k.layer.edit("${ctx}/admin/employee/detail?id=" + id);
}

var page = "${page.page}";

function reload() {
	page_submit(page);
}
function del() {
    var selections = $('#table').bootstrapTable('getAllSelections');
    if (selections.length < 1) {
        error2(k.msg.requireOne);
        return;
    }
    var ids = new Array() ;
    for(var i = 0 ; i < selections.length ; i++)
        ids[i] = selections[i].id;
    $.ajax({
        url: "${ctx}/admin/employee/delete",
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
paging('pagination', ${page.totalPages}, ${page.page});

</script>
</body>
</html>