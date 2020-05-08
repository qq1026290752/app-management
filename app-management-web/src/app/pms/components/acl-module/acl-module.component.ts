import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-acl-module',
  templateUrl: './acl-module.component.html',
  styleUrls: ['./acl-module.component.scss']
})
export class AclModuleComponent implements OnInit {
  isDisabled: boolean;

  constructor() { }

  ngOnInit() {
    this.isDisabled = false;
  }

}
