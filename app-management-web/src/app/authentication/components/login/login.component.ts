import {Component,OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
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
  public form:FormGroup;
  user = {};

  constructor(private http:HttpClient,
              private notify: ElNotificationService,
              private fb:FormBuilder,
              private router:Router) { }
  ngOnInit() {
      this.form = this.fb.group({
        userName: ['1026290752@qq.com',Validators.compose([Validators.email,Validators.required])],
        password: ['',Validators.required]
      })

  }

  onLogin({value,valid}, ev: Event) {
    ev.defaultPrevented
    let headers = new HttpHeaders();
    headers= headers.set('content-type', 'application/json')
    this.http.post("/pms/login",JSON.stringify(value), {headers}).subscribe((res)=>{
        console.log("login success")
         this.router.navigate(['/pms/dashboard']).then(r => r);
    },()=>{
      this.notify.error("登陆失败,请重新登陆","登陆提示")
    })
  }
}
