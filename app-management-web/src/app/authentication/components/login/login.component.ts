import {Component, forwardRef, Inject, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {FormBuilder, FormControl, FormGroup, Validator, Validators} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent implements OnInit {

  color = 'primary';
  checked = true;
  disabled = false;
  user = {userName:'',passWord:'',check:true};

  constructor(private http:HttpClient,
              @Inject(forwardRef(() => FormBuilder)) private formBuilder: FormBuilder) { }
  userName = new FormControl('', [Validators.required,Validators.email]);
  password= new FormControl('', [Validators.required]);


  ngOnInit() {
    this.user.check = false;
  }

  onLogin() {
      this.http.post("/login",JSON.stringify(this.user)).subscribe((res)=>{
        console.log(res);
      })
  }
  getUserNameErrorMessage() {
    return this.userName.hasError('required')?'请输入用户名':
      this.userName.hasError('email') ? '请输入正确的邮箱地址':''
  }

  getPasswordErrorMessage() {
    return this.password.hasError('required')?'请输入密码':''
  }
}
