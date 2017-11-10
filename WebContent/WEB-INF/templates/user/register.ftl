<form id="form1" name="form1" method="post" action="${base!}/doRegister">
  <table width="100%" border="0" cellspacing="0" cellpadding="5">
  <thead>register</thead>
    <tr>
      <td>用户名：</td>
      <td><input name="name" type="text" id="name" /></td>
    </tr>
    <tr>
      <td>密码：</td>
      <td><input name="password" type="password" id="password" /></td>
    </tr>
        <tr>
      <td>验证码：</td>
      <td><input name="captcha" type="text" id="captcha" /></td>
    </tr>
            <tr>
      <td>短信验证码：</td>
      <td><input name="smsCaptcha" type="text" id="smsCaptcha" /></td>
    </tr>
    <tr>
      <td colspan="2"><input type="submit" name="Submit" value="提交" /></td>
    </tr>
  </table>
</form>