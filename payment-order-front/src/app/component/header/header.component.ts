import { Component } from '@angular/core';
import { TITLE } from 'src/app/model/title';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  title: string = TITLE;
}
