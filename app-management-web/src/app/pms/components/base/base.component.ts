import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ElNotificationService} from "element-angular/release/element-angular.module";

@Component({
  selector: 'app-base',
  templateUrl: './base.component.html',
  styleUrls: ['./base.component.scss']
})
export class BaseComponent implements OnInit {

  constructor(private http:HttpClient,
              private notify: ElNotificationService) { }

  ngOnInit() {
   console.log("ngOnInit")
    this.http.get("/pms/getMe").subscribe((res)=> this.notify.success('当前用户为:'+res,"信息获取成功"),()=>{
      this.notify.error("系统请求错误","系统错误")
    })
  }

}
