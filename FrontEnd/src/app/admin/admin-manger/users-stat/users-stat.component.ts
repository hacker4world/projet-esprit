import { Component, OnInit, Inject, PLATFORM_ID, AfterViewInit } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { UserDTO } from '../../../models/user.dto';
import { UserService } from '../../../service/user.service';
import ApexCharts from 'apexcharts';
import jsPDF from 'jspdf';
import html2canvas from 'html2canvas';

interface RoleCounts {
  [key: string]: number;
  student: number;
  admin: number;
  professor: number;
  entreprise: number;
}

interface OptionCounts {
  [key: string]: number;
  DEVOPS: number;
  GL: number;
  CLOUD: number;
  BI: number;
  TWIN: number;
  MOBILE: number;
}

@Component({
  selector: 'app-users-stat',
  templateUrl: './users-stat.component.html',
  styleUrls: ['./users-stat.component.css']
})
export class UsersStatComponent implements OnInit {
  monthNames: string[] = ['January', 'February', 'March', 'April', 'May', 'June',
    'July', 'August', 'September', 'October', 'November', 'December'];
  roleCountsvalue(value: string) {
    return this.roleCounts[value];
  }
  selectedMonth: string = '';
  chartData: any[] = [];
  users: UserDTO[] = [];
  currentUserId: any;
  selectedUser: UserDTO | null = null;
  searchText: string = '';
  roleCounts: RoleCounts = {
    student: 0,
    admin: 0,
    professor: 0,
    entreprise: 0
  };

  optionCounts: OptionCounts = {
    DEVOPS: 0,
    GL: 0,
    CLOUD: 0,
    BI: 0,
    TWIN: 0,
    MOBILE: 0
  };

  private chartInstance: ApexCharts | null = null;
  barChartData!: number[][];
  barChartLabels!: string[];

  constructor(private userService: UserService, @Inject(PLATFORM_ID) private platformId: any) { }

  ngOnInit(): void {
    this.getAllUsers();
    if (this.users.length > 0) {
      this.onMonthChange();
    }
  }
  onMonthChange() {
    const filteredUsers = this.users.filter(user => {
      const isInSelectedMonth = new Date(user.createdDate).getMonth() === this.getMonthIndex(this.selectedMonth);
      return isInSelectedMonth;
    });

    const inscriptionCounts: number[] = new Array(31).fill(0);

    filteredUsers.forEach(user => {
      const day = new Date(user.createdDate).getDate() - 1;
      inscriptionCounts[day]++;
    });

    this.chartData = [{
      name: 'Inscriptions',
      data: inscriptionCounts
    }];
    this.renderChart(this.chartData);
  }

  getMonthIndex(monthName: string): number {
    return this.monthNames.indexOf(monthName);
  }

  renderChart(chartData: any): void {
    const chartElement = document.querySelector('#chart4');
    if (!chartElement) {
      console.error('Chart element not found');
      return;
    }

    if (!this.chartInstance) {
      const options = {
        chart: {
          type: 'line',
          height: 399,
          toolbar: {
            show: false
          }
        },
        series: chartData,
        xaxis: {
          categories: [...Array(31).keys()].map(i => i + 1)
        },
        yaxis: {
          title: {
            text: 'Number of Inscriptions'
          }
        }
      };
      this.chartInstance = new ApexCharts(chartElement, options);
      this.chartInstance.render();
    } else {
      this.chartInstance.updateSeries(chartData);
    }
  }


  getAllUsers(): void {
    this.userService.getAllUsers().subscribe(data => {
      this.users = data;
      this.countUsersByRole();
      this.countUsersByOption();
      if (isPlatformBrowser(this.platformId)) {
        this.initializeChart();
      }
    });
  }

  countUsersByRole(): void {
    const roles = ['STUDENT', 'PROFESSOR', 'ENTERPRISE', 'ADMIN'];

    roles.forEach(role => {
      this.roleCounts[role.toLowerCase()] = this.users.filter(user => user.role.toLowerCase() === role.toLowerCase()).length;
    });

    console.log('Users by Role:', this.roleCounts);
  }

  countUsersByOption(): void {
    const options = ['DEVOPS', 'GL', 'CLOUD', 'BI', 'TWIN', 'MOBILE'];
    options.forEach(option => {
      this.optionCounts[option] = this.users.filter(user => user?.userOption === option).length;
    });

    this.barChartData = [
      [...Object.values(this.optionCounts)]
    ];
    this.barChartLabels = options;
    console.log('Users by Option:', this.optionCounts);
  }

  initializeChart(): void {
    const options = {
      chart: {
        height: 339,
        type: 'bar',
        toolbar: {
          show: false
        }
      },
      series: [{
        name: 'Users',
        data: Object.values(this.optionCounts)
      }],
      xaxis: {
        categories: ['DEVOPS', 'GL', 'CLOUD', 'BI', 'TWIN', 'MOBILE'],
      },
      colors: ['#9767FD', '#dfe2e6', '#f1b44c'],
      plotOptions: {
        bar: {
          columnWidth: '30%'
        }
      },
      stroke: {
        width: [0, 2, 4],
        curve: 'smooth'
      },
      tooltip: {
        shared: true,
        intersect: false,
        y: {
          formatter: function (val: number) {
            return val + ' users';
          }
        }
      },
      grid: {
        borderColor: '#f1f1f1'
      },
    };

    const chartElement = document.querySelector('#management_bar');
    if (chartElement) {
      const chart = new ApexCharts(chartElement, options);
      chart.render();
    }
  }
  public generatePDF() {
    const doc = new jsPDF();
    const element = document.getElementById('pdfContent'); // HTML element to convert

    if (element) {
        html2canvas(element).then(canvas => {
            const imageData = canvas.toDataURL('image/png'); // Get image data from canvas

            const pdfWidth = doc.internal.pageSize.getWidth(); // Get page width
            const pdfHeight = doc.internal.pageSize.getHeight(); // Get page height

            const canvasWidth = canvas.width;
            const canvasHeight = canvas.height;

            // Calculate the scale factor to fit the canvas content within the PDF page
            const scaleFactor = Math.min(pdfWidth / canvasWidth, pdfHeight / canvasHeight);

            const imageWidth = canvasWidth * scaleFactor;
            const imageHeight = canvasHeight * scaleFactor;

            // Center the image on the PDF page
            const marginX = (pdfWidth - imageWidth) / 2;
            const marginY = (pdfHeight - imageHeight) / 2;

            doc.addImage(imageData, 'PNG', marginX, marginY, imageWidth, imageHeight);
            doc.save('my_document.pdf');
        });
    }
}


}