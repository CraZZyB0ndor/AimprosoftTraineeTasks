import {Component, OnInit} from '@angular/core';
import {NavigationEnd, Router} from "@angular/router";
import {UrlUtils} from "../../../utils/UrlUtils";

@Component({
  selector: 'app-main-layout',
  templateUrl: './main-layout.component.html',
  styleUrls: ['./main-layout.component.scss']
})
export class MainLayoutComponent implements OnInit {

  currentRoute?: string;
  params?: any[];
  controlButtons: { router: string, text?: string, iconName?: string }[] = [];

  constructor(private router: Router) {

  }

  ngOnInit(): void {
    this.routeListener();
  }

  createControlElements(url: string) {
    switch (url) {
      case "departments":
        this.controlButtons = [
          {router: 'departments/create', text: 'Create department'}
        ];
        break;
      case "departments/employees":
        this.controlButtons = [
          {router: `departments/${this.params![0]}/employees/create`, text: 'Create employee'},
          {router: 'departments', iconName: 'keyboard_backspace'}
        ];
        break;
      case "departments/create":
      case "departments/edit":
        this.controlButtons = [
          {router: 'departments', iconName: 'keyboard_backspace'}
        ];
        break;
      case "departments/employees/create":
      case "departments/employees/edit":
        this.controlButtons = [
          {router: `departments/${this.params![0]}/employees`, iconName: 'keyboard_backspace'}
        ];
        break;
      default:
        this.controlButtons = [];
    }
  }


  routeListener() {
    this.router.events.subscribe((event => {
      if (event instanceof NavigationEnd) {
        this.params = UrlUtils.getParams(event.urlAfterRedirects);
        let url = UrlUtils.getSimpleUrl(event.urlAfterRedirects)
          .replace(/(^\/)|(\/$)/g, '');
        this.createControlElements(url);
        url = url.replace(/\//g, ' / ').toUpperCase();
        this.currentRoute = url;
      }
    }))
  }
}
