<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
</head>

<body>
	<div style="text-align: center; width: 100%">
		<div style="width: 550px; margin: auto;">
			<div style="width: 100%;">
				<form action="${ctx}/sysUser" method="post">
					职级：
					<select name="level">
						<option value="">&nbsp;</option>
						<c:forEach items="${typeList}" var="type">
							<option value="${type.code}" <c:if test="${criteria.level eq type.code}">selected="selected"</c:if>>${type.name}</option>
						</c:forEach>
					</select>
					&nbsp;&nbsp;
					姓名：
					<input type="text" name="userName" value="${criteria.userName}" />
					&nbsp;
					<input type="submit" value="查询" />
					<input type="button" value="新增" onclick="toEdit()" />
				</form>
			</div>
			<div style="height: 20px;"></div>
			<div style="width: 100%;">
				<table border="1" cellpadding="0" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th>姓名</th>
							<th>职级</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${fn:length(userList) gt 0}">
								<c:forEach items="${userList}" var="user">
									<tr>
										<td>${user.userName}</td>
										<td>${user.level}</td>
										<td>
											<input type="button" value="修改" onclick="toEdit(${user.id})" />
											<input type="button" value="删除" onclick="del(${user.id})" />
										</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr><td colspan="3">暂无数据</td></tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
			<div style="height: 20px;"></div>
			<div style="height: 20px;">主机：${host}</div>
		</div>
	</div>
</body>
<script type="text/javascript">
function toEdit(id) {
	window.location.href = "${ctx}/sysUser/toEditUser?id=" + (id ? id : "");
}

function del(id) {
	window.location.href = "${ctx}/sysUser/delUser?id=" + (id ? id : "");
}
</script>
</html>

