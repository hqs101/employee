<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="enum" uri="http://www.hnluchuan.com/enum" %>

<%@include file="/common/head.jsp" %>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <form action="${ctx }/admin/employee/add_employee" id="detail-form" class="form-horizontal" method="post">
        <!-- 用户基本信息 -->
        <div style="text-align: center ; font-size: 20px ; font-weight: bold"><p>员工基本信息</p></div>
        <div class="form-group">
            <!-- 用户名 -->
            <label class="col-sm-2 control-label">用户名</label>
            <div class="col-sm-4">
                <input id="userid" name="user.username" value="" class="form-control" onblur="checkusername(this)">
                <span id="username_err" style="display: none; color: red;">用户名不能为空</span>
            </div>
            <!-- 邮箱 -->
            <label class="col-sm-2 control-label">邮箱</label>
            <div class="col-sm-4">
                <input id="email" name="user.email" value="" class="form-control" onblur="checkemail(this)">
                <span id="email_err" style="display: none; color: red;">邮箱为空或者格式不正确！</span>
            </div>
        </div>
        <div class="form-group">
            <!-- 岗位 -->
            <label class="col-sm-2 control-label">岗位</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="employee.position" value="">
            </div>
            <!-- 员工状态/1.录用/2.停职 -->
            <label class="col-sm-2 control-label">员工状态</label>
            <div class="col-sm-4">
               <select name="employee.status" class="form-control">
                   <option value="1">录用</option>
                   <option value="2">停职</option>
               </select>
            </div>
        </div>
        <div class="form-group">

            <!-- 姓名 -->
            <label class="col-sm-2 control-label">姓名</label>
            <div class="col-sm-4">
                <input id="name" type="text" class="form-control" name="employee.name" value="" onblur="chenkname(this)">
                <span id="name_err" style="display: none; color: red;">姓名不能为空！</span>
            </div>

            <!-- 出生日期 -->
            <label class="col-sm-2 control-label">出生日期</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="employee.birth" value="" onclick="laydate({format: ''})">
            </div>
        </div>
        <div class="form-group">

            <!-- 性别 -->
            <label class="col-sm-2 control-label">性别</label>
            <div class="col-sm-4">
                <select name="employee.sex" class="form-control">
                    <option value="1">男</option>
                    <option value="2">女</option>
                </select>
            </div>

            <!-- 身高 -->
            <label class="col-sm-2 control-label">身高</label>
            <div class="col-sm-4">
                <input id="height" type="text" class="form-control" name="employee.height" value="" onblur="checkheight(this)">
                <span id="height_err" style="display: none; color: red;">身高格式不正确！</span>
            </div>
        </div>
        <div class="form-group">

            <!-- 体重 -->
            <label class="col-sm-2 control-label">体重</label>
            <div class="col-sm-4">
                <input id="width" type="text" class="form-control" name="employee.weight" value="" onblur="checkwidth(this)">
                <span id="width_err" style="display: none; color: red;">体重格式不正确！</span>
            </div>

            <!-- 籍贯 -->
            <label class="col-sm-2 control-label">籍贯</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="employee.place" value="">
            </div>
        </div>
        <div class="form-group">

            <!-- 民族 -->
            <label class="col-sm-2 control-label">民族</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="employee.nation" value="">
            </div>

            <!-- 联系电话 -->
            <label class="col-sm-2 control-label">联系电话</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="employee.phone" value="">
            </div>
        </div>
        <div class="form-group">

            <!-- 身份证号 -->
            <label class="col-sm-2 control-label">身份证号</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="employee.iDCard" value="">
            </div>

            <!-- 毕业学校 -->
            <label class="col-sm-2 control-label">毕业学校</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="employee.school" value="">
            </div>
        </div>
        <div class="form-group">

            <!-- 专业 -->
            <label class="col-sm-2 control-label">专业</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="employee.major" value="">
            </div>

            <!-- 学历 -->
            <label class="col-sm-2 control-label">学历</label>
            <div class="col-sm-4">
                <select name="employee.education" class="form-control">
                    <option value="1">小学</option>
                    <option value="2">初中</option>
                    <option value="3">高中</option>
                    <option value="4">本科</option>
                    <option value="5">硕士</option>
                    <option value="6">博士</option>
                    <option value="7">其他</option>
                </select>
            </div>
        </div>
        <div class="form-group">

            <!-- 户口所在地 -->
            <label class="col-sm-2 control-label">户口所在地</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="employee.location" value="">
            </div>

            <!-- 居住地址 -->
            <label class="col-sm-2 control-label">居住地址</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="employee.address" value="">
            </div>
        </div>
        <div class="form-group">

            <!-- 微信号 -->
            <label class="col-sm-2 control-label">微信号</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="employee.wechat" value="">
            </div>
            <!-- 婚姻状况 -->
            <label class="col-sm-2 control-label">婚姻状况</label>
            <div class="col-sm-4">
                <select name="employee.marry" class="form-control">
                    <option value="1">未婚</option>
                    <option value="2">已婚</option>
                    <option value="3">离异</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <!-- 入职时间 -->
            <label class="col-sm-2 control-label">入职时间</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" name="employee.inTime" value="" onclick="laydate({format: ''})">
            </div>
        </div>

        <!--员工学历信息开始-->
        <div style="text-align: center ; font-size: 20px ; font-weight: bold"><p>员工学历信息</p></div>
        <div>
            <!--教育克隆模板-->
            <div id="clone_e" style="display: none;">
                <button type="button" class="btn btn-sm btn-outline btn-danger" onclick="del(this);">删除</button>
                <input type="hidden" name="educations[10].id" value="" class="form-control">
                <div class="form-group">
                    <label class="col-sm-2 control-label">学习起始时间</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="educations[10].start" value="" onclick="laydate({format: ''})">
                    </div>
                    <!-- 学习结束时间 -->
                    <label class="col-sm-2 control-label">学习结束时间</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="educations[10].end" value="" onclick="laydate({format: ''})">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">专业</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="educations[10].major" value="">
                    </div>
                    <!-- 学历 -->
                    <label class="col-sm-2 control-label">学历</label>
                    <div class="col-sm-4">
                        <select name="educations[10].record" class="form-control">
                            <option value="1">小学</option>
                            <option value="2">初中</option>
                            <option value="3">高中</option>
                            <option value="4">本科</option>
                            <option value="5">硕士</option>
                            <option value="6">博士</option>
                            <option value="7">其他</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">证书</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="educations[10].certificate" value="">
                    </div>
                </div>
            </div>
            <div id="education_con">
            </div>
            <div style="text-align: center">
                <p>
                    <button type="button" class="btn btn-sm btn-outline btn-primary" onclick="add_education()"><i class="fa fa-plus"></i> 新增</button>
                </p>
            </div>
        </div>

        <!--员工紧急联系人信息开始-->
        <div style="text-align: center ; font-size: 20px ; font-weight: bold"><p>员工紧急联系人信息</p></div>
        <div>
            <!--列表克隆模板-->
            <div id="clone_em" style="display: none">
                <button type="button" class="btn btn-sm btn-outline btn-danger" onclick="del(this);">删除</button>
                <input type="hidden" name="emergencys[10].id" value="" class="form-control">
                <div class="form-group">
                    <label class="col-sm-2 control-label">姓名</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="emergencys[10].name" value="">
                    </div>
                    <label class="col-sm-2 control-label">关系</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="emergencys[10].relation" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">联系电话</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="emergencys[10].phone" value="">
                    </div>
                    <label class="col-sm-2 control-label">单位或住址</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="emergencys[10].address" value="">
                    </div>
                </div>
            </div>
            <div id="emergencyInfo_con">
            </div>
            <div style="text-align: center">
                <p>
                    <button type="button" class="btn btn-sm btn-outline btn-primary" onclick="add_emergency()"><i class="fa fa-plus"></i> 新增</button>
                </p>
            </div>
        </div>

        <!--员工工作经验信息开始-->
        <div style="text-align: center ; font-size: 20px ; font-weight: bold"><p>员工工作经验信息</p></div>
        <div>
            <!--克隆模板-->
            <div id="clone_ex" style="display: none">
                <input type="hidden"name="experiences[10].id" value="" class="form-control">
                <button type="button" class="btn btn-sm btn-outline btn-danger" onclick="del(this);">删除</button>
                <div class="form-group">
                    <label class="col-sm-2 control-label">离职原因</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="experiences[10].reason" value="">
                    </div>
                    <label class="col-sm-2 control-label">单位</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="experiences[10].company" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">工作</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="experiences[10].work" value="">
                    </div>
                    <label class="col-sm-2 control-label">职位</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="experiences[10].position" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">工作起始时间</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="experiences[10].start" value="" onclick="laydate({format: ''})">
                    </div>
                    <label class="col-sm-2 control-label">工作结束时间</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="experiences[10].end" value="" onclick="laydate({format: ''})">
                    </div>
                </div>
            </div>
            <div id="expericnceInfo_con">
            </div>
            <div style="text-align: center">
                <p>
                    <button type="button" class="btn btn-sm btn-outline btn-primary" onclick="add_expericnce()"><i class="fa fa-plus"></i> 新增</button>
                </p>
            </div>
        </div>

        <!--员工家庭成员信息开始-->
        <div style="text-align: center ; font-size: 20px ; font-weight: bold"><p>员工家庭成员信息</p></div>
        <div>
            <!--家庭克隆模板-->
            <div id="clone_f" name="clone_name" style="display: none">
                <input type="hidden" name="families[10].id" value="" class="form-control">
                <button type="button" class="btn btn-sm btn-outline btn-danger" onclick="del(this);">删除</button>
                <div class="form-group">
                    <label class="col-sm-2 control-label">姓名</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="families[10].name" value="">
                    </div>
                    <label class="col-sm-2 control-label">关系</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="families[10].relation" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">联系方式</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="families[10].contact" value="">
                    </div>
                    <label class="col-sm-2 control-label">年龄</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="families[10].age" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">工作单位</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="families[10].company" value="">
                    </div>
                    <label class="col-sm-2 control-label">职位</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="families[10].position" value="">
                    </div>

                </div>
            </div>
            <div id="family_con">
            </div>
            <div style="text-align: center">
                <p>
                    <button type="button" class="btn btn-sm btn-outline btn-primary" onclick="add_family()"><i class="fa fa-plus"></i> 新增</button>
                </p>
            </div>
        </div>
    </form>
</div>

<%@include file="/common/foot.jsp" %>
<script>
    function add_emergency(){
        var div_ = document.getElementById("clone_em").cloneNode(true);
        var inputs = div_.getElementsByTagName("input");
        var e_div_sum = document.getElementById("emergencyInfo_con").childElementCount;
        for(var i = 0 ; i < inputs.length ; i++){
            var inputName = inputs[i].name;
            var newInputName = inputName.replace(/[0-9]+/ , e_div_sum);
            inputs[i].name = newInputName;
        }
        div_.style.display = "block";
        document.getElementById("emergencyInfo_con").appendChild(div_);

        var div_clone = document.getElementById("clone_em");
        var clone_inputs = div_clone.getElementsByTagName("input");
        for(var j = 0 ; j < clone_inputs.length ; j++){
            var inputName = clone_inputs[j].name;
            var newInputName = inputName.replace(/[0-9]+/ , (e_div_sum + 1));
            clone_inputs[j].name = newInputName;
        }
    }

    function add_education(){
        var div_ = document.getElementById("clone_e").cloneNode(true);
        var inputs = div_.getElementsByTagName("input");
        var e_div_sum = document.getElementById("education_con").childElementCount;
        for(var i = 0 ; i < inputs.length ; i++){
            var inputName = inputs[i].name;
            var newInputName = inputName.replace(/[0-9]+/ , e_div_sum);
            inputs[i].name = newInputName;
        }
        var selects = div_.getElementsByTagName("select");
        for(var k = 0 ; k < selects.length ; k++ ){
            var selectname = selects[k].name ;
            var newname = selectname.replace(/[0-9]+/ , e_div_sum);
            selects[k].name = newname;
        }

        div_.style.display = "block";
        div_.id = "educationInfo";
        div_.name = "educationName";
        document.getElementById("education_con").appendChild(div_);

        var div_clone = document.getElementById("clone_e");
        var clone_inputs = div_clone.getElementsByTagName("input");
        for(var j = 0 ; j < clone_inputs.length; j++){
            var inputName = clone_inputs[j].name;
            var newInputName = inputName.replace(/[0-9]+/ , (e_div_sum + 1));
            clone_inputs[j].name = newInputName;
        }
        var selects_clone = div_clone.getElementsByTagName("select");
        for(var ks = 0 ; ks < selects_clone.length ; ks++ ){
            var selectname = selects_clone[ks].name ;
            var newname = selectname.replace(/[0-9]+/ , (e_div_sum + 1));
            selects_clone[ks].name = newname;
        }
    }

    function add_expericnce(){
        var div_ = document.getElementById("clone_ex").cloneNode(true);
        var inputs = div_.getElementsByTagName("input");
        var ex_div_sum = document.getElementById("expericnceInfo_con").childElementCount;
        for(var i = 0 ; i < inputs.length ; i++){
            var inputName = inputs[i].name;
            var newInputName = inputName.replace(/[0-9]+/ , ex_div_sum);
            inputs[i].name = newInputName;
        }
        div_.style.display = "block";
        div_.id = "expericnceInfo";
        div_.name = "experienceName"
        document.getElementById("expericnceInfo_con").appendChild(div_);

        var div_clone = document.getElementById("clone_ex");
        var clone_inputs = div_clone.getElementsByTagName("input");
        for(var j = 0 ; j < clone_inputs.length; j++){
            var inputName = clone_inputs[j].name;
            var newInputName = inputName.replace(/[0-9]+/ , (ex_div_sum + 1));
            clone_inputs[j].name = newInputName;
        }
    }

    function add_family(){
        var div_ = document.getElementById("clone_f").cloneNode(true);
        var inputs = div_.getElementsByTagName("input");
        var f_div_sum = document.getElementById("family_con").childElementCount;
        for(var i = 0 ; i < inputs.length ; i++){
            var inputName = inputs[i].name;
            var newInputName = inputName.replace(/[0-9]+/ , f_div_sum);
            inputs[i].name = newInputName;
        }
        div_.style.display="block";
        div_.id = "familyInfo";
        div_.name = "familyName";
        document.getElementById("family_con").appendChild(div_);

        var div_clone = document.getElementById("clone_f");
        var clone_inputs = div_clone.getElementsByTagName("input");
        for(var j = 0 ; j < clone_inputs.length; j++){
            var inputName = clone_inputs[j].name;
            var newInputName = inputName.replace(/[0-9]+/ , (f_div_sum + 1));
            clone_inputs[j].name = newInputName;
        }
    }

    function del(button){
        var div1 = button.parentElement;
        div1.remove();
    }

    function checkusername(username) {
        var user_name =  username.value;
        if(user_name == ""){
           document.getElementById("userid").focus();
           document.getElementById("username_err").style.display = "block";
        }else{
            document.getElementById("username_err").style.display = "none";
        }
    }

    function checkemail(emailcheck) {
        var reg = new RegExp("^[a-zA-Z0-9]+@[a-zA-Z0-9]+.[a-zA-Z0-9]+$");
        var emailvalue = emailcheck.value;
        if(emailvalue == "" || !reg.test(emailvalue)){
            document.getElementById("email").focus();
            document.getElementById("email_err").style.display = "block";
        }else{
            document.getElementById("email_err").style.display = "none";
        }
    }

    function checkheight(height) {
        var reg = new RegExp("^\\d+(\\.\\d+)?$");
        var heightvalue = height.value;
        if(heightvalue == "" || !reg.test(heightvalue)){
            document.getElementById("height").focus();
            document.getElementById("height_err").style.display = "block";
        }else{
            document.getElementById("height_err").style.display = "none";
        }
    }

    function checkwidth(height) {
        var reg = new RegExp("^\\d+(\\.\\d+)?$");
        var widthvalue = width.value;
        if(widthvalue == "" || !reg.test(widthvalue)){
            document.getElementById("width").focus();
            document.getElementById("width_err").style.display = "block";
        }else{
            document.getElementById("width_err").style.display = "none";
        }
    }

    function chenkname(name) {
        var names =  name.value;
        if(names == ""){
            document.getElementById("name").focus();
            document.getElementById("name_err").style.display = "block";
        }else{
            document.getElementById("name_err").style.display = "none";
        }
    }
</script>
</body>
</html>