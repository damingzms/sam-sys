<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息</title>
</head>

<body>
	<div style="text-align: center; width: 100%">
		<div style="width: 550px; margin: auto;">
			<div style="width: 100%;">
				<form action="${ctx}/sysUser/saveUser" method="post">
					<input type="hidden" name="id" value="${user.id}" />
					职级：
					<select name="level">
						<option value="">&nbsp;</option>
						<c:forEach items="${typeList}" var="type">
							<option value="${type.code}" <c:if test="${user.level eq type.code}">selected="selected"</c:if>>${type.name}</option>
						</c:forEach>
					</select>
					&nbsp;&nbsp;
					姓名：
					<input type="text" name="userName" value="${user.userName}" />
					&nbsp;
					<input type="submit" value="保存" />
				</form>
			</div>
		</div>
	</div>
</body>
</html>

