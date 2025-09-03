import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'maskAadhaar', standalone: true })
export class MaskAadhaarPipe implements PipeTransform {
  transform(value?: string): string {
    if (!value) return '';
    const clean = value.replace(/\D/g, '');
    if (clean.length !== 12) return value;
    return 'XXXX-XXXX-' + clean.slice(-4);
  }
}

@Pipe({ name: 'maskPan', standalone: true })
export class MaskPanPipe implements PipeTransform {
  transform(value?: string): string {
    if (!value) return '';
    if (!/^[A-Z]{5}[0-9]{4}[A-Z]$/.test(value)) return value;
    return value.slice(0,5) + '-XXXX-' + value.slice(-1);
  }
}
