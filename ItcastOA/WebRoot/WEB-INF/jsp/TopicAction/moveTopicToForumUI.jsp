<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<head>
<title>移动主题</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/style/blue/forum.css">
</head>
<body>
	<!-- 标题显示 -->
	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img
					src="${pageContext.request.contextPath}/style/images/title_arrow.gif"
					width="13" height="13" border="0"> 移动主题
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<!--显示表单内容-->
	<div id="MainArea">
		<s:form action="topic_moveTopicToForum?id=%{#topic.id}">
			<div id="PageHead"></div>
			<center>
				<div class="ItemBlock_Title1">
					<div width="85%" style="float: left">
						<font class="MenuPoint"> &gt; </font> <a
							href="${pageContext.request.contextPath}/BBS_Forum/forumList.html">论坛</a>
						<font class="MenuPoint"> &gt; </font>
						<s:a action="forum_show?id=%{#topic.forum.id}">${topic.forum.name}</s:a>
						<font class="MenuPoint"> &gt;&gt; </font> 移动主题
					</div>
				</div>
				<div class="ItemBlockBorder">
					<table id="InputArea" width="100%" cellspacing="1" cellpadding="1"
						border="0">
						<tbody>
							<tr>
								<td class="InputAreaBg" height="30"><div class="InputTitle">帖子主题</div></td>
								<td class="InputAreaBg"><div class="InputContent">${topic.title}</div></td>
							</tr>
							<tr>
								<td class="InputAreaBg" height="30"><div class="InputTitle">移动到</div></td>
								<td class="InputAreaBg"><div class="InputContent">
										<s:select name="destForumId" list="forumList" listKey="id"
											listValue="name">
										</s:select>
									</div></td>
							</tr>
							<tr height="30">
								<td class="InputAreaBg" colspan="2" align="center"><input
									src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG"
									style="margin-right: 15px;" type="image"> <a
									href="javascript:history.go(-1);"><img
										src="${pageContext.request.contextPath}/style/blue/images/button/goBack.png"></a>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</center>
		</s:form>

	</div>

	<div class="Description">
		说明：<br>

	</div>



</body>
