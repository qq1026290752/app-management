import {Component, forwardRef, Inject, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {FormBuilder, FormControl, FormGroup, Validator, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {ElNotificationService} from "element-angular/release/element-angular.module";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent implements OnInit {

  color = 'primary';
  checked = false;
  disabled = false;
  user = {userName:'1026290752@qq.com',passWord:'123456',check:true};

  constructor(private http:HttpClient,
              @Inject(forwardRef(() => FormBuilder)) private formBuilder: FormBuilder,
              private notify: ElNotificationService,
              private router:Router) { }
  userName = new FormControl('', [Validators.required,Validators.email]);
  password= new FormControl('', [Validators.required]);


  ngOnInit() {
  }

  onLogin() {
    let headers = new HttpHeaders();
    headers= headers.set('content-type', 'application/json')
    this.http.post("/pms/login",JSON.stringify(this.user), {headers}).subscribe((res)=>{
        console.log("login success")
         this.router.navigate(['/pms/dashboard']).then(r => r);
    },()=>{
      this.notify.error("登陆失败,请重新登陆","登陆提示")
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
